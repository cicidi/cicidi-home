package com.cicidi.home.controller;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.ProfileVo;
import com.cicidi.home.service.CrawlerService;
import com.cicidi.home.service.EntityService;
import com.cicidi.home.service.GitHubService;
import com.cicidi.home.service.GoogleMapService;
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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
class HomeController {

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

    // spring data rest use profile as data profile
    @GetMapping("/resumeProfile")
    String resumeProfile(Model model, HttpServletRequest request, Principal principal) throws Exception {

        Profile profile = entityService.loadAndUpdate();
        ProfileVo profileVo = new ProfileVo(profile);
        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(profile));
        model.addAttribute("profileVo", profileVo);
        return "profile";

    }

    @GetMapping("/linkedinProfile")
    String linkedinProfile(Model model, HttpServletRequest request, Principal principal) throws Exception {

        Connection<LinkedIn> connection = connectionRepository.findPrimaryConnection(LinkedIn.class);
        if (connection == null) {
            return "redirect:/connect/linkedin";
        }
        String url = connection.getApi().profileOperations().getUserProfile().getPublicProfileUrl();
        LinkedInProfileFull linkedInProfileFull = connection.getApi().profileOperations().getProfileFullByPublicUrl(url);
        ProfileVo profileVo = new ProfileVo(linkedInProfileFull);
        profileVo.addPositions(crawlerService.fetchByUrl(linkedInProfileFull.getPublicProfileUrl()));
        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(profileVo));
        model.addAttribute("profileVo", profileVo);
        return "linkedinProfile";

    }

    @GetMapping("/home")
    String option(Model model, HttpServletRequest request, Principal principal) throws Exception {
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
