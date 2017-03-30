package com.cicidi.home.controller;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.ProfileVo;
import com.cicidi.home.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
class HomeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    GoogleMapService googleMapService;

    @Autowired
    GitHubService gitHubService;

    @Autowired
    EntityService entityService;

    @Autowired
    CrawlerService crawlerService;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    AccountService accountService;


    public
    // spring data rest use profile as data profile
    @GetMapping("/profile/{username}")
    String resumeProfile(Model model, @PathVariable String username) throws Exception {
        Profile profile = profileService.getProfile(username);
        ProfileVo profileVo = new ProfileVo(profile);
        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(profile));
        model.addAttribute("profileVo", profileVo);

        // redirect to different template;
        if (username.equals("walter_chen"))
            return "profile";
        else {
            return "linkedinProfile";
        }
    }

    @GetMapping("/profiles")
    String resumeProfile(Model model, HttpServletRequest request) throws Exception {
        return "redirect:/profile" + "/" + request.getRemoteUser();

    }

    @GetMapping("/linkedinProfile")
    String linkedinProfile(Model model, HttpServletRequest request) throws Exception {
        ProfileVo profileVo;
        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
        if (connection == null) {
            //if login by username and password,.connection is null
            Profile profile = profileService.getProfile(request.getRemoteUser());
            profileVo = profile != null ? new ProfileVo(profile) : null;
        } else {
            String url = connection.getApi().profileOperations().getUserProfile().getPublicProfileUrl();
            LinkedInProfileFull linkedInProfileFull = connection.getApi().profileOperations().getProfileFullByPublicUrl(url);
            profileVo = new ProfileVo(linkedInProfileFull);
            profileVo.addPositions(crawlerService.fetchByUrl(linkedInProfileFull.getPublicProfileUrl()));
        }

        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(profileVo));
        model.addAttribute("profileVo", profileVo);
        return "linkedinProfile";

    }

    @GetMapping({"/", "/home"})
    String option(Model model, Principal principal) throws Exception {


        String username = null;
        if (principal != null) {
            if (principal instanceof UsernamePasswordAuthenticationToken) {
                username = principal.getName();
            }
        }
        Profile profile = profileService.getProfile(username);
        model.addAttribute("username", username);
        if (profile != null) {
            model.addAttribute("firstName", profile.getFirstName());
            model.addAttribute("linkedInUser", true);
        } else {
            model.addAttribute("linkedInUser", false);
        }
        return "option";
    }

    //only use for test purpose
//    @GetMapping("/deleteProfile/{username}")
//    @ResponseBody
//    String delelteProfile(@PathVariable String username) {
//        profileService.delete(username);
//        return "200";
//    }
//
//    @GetMapping("/deleteAccount/{username}")
//    @ResponseBody
//    String deletepAccount(@PathVariable String username) {
//        accountService.deleteAccount(username);
//        return "200";
//    }
}
