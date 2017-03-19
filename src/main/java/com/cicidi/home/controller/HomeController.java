package com.cicidi.home.controller;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.ProfileVo;
import com.cicidi.home.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
import java.util.Map;

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
        String name = null;
        if (principal != null) {
            if (principal instanceof UsernamePasswordAuthenticationToken) {
                name = principal.getName();
            } else {
                name = (String) ((Map) (((OAuth2Authentication) principal).getUserAuthentication().getDetails())).get("firstName");
            }
        }
        model.addAttribute("name", name);
        return "option";
    }
}
