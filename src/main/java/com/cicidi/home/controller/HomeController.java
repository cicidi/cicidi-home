package com.cicidi.home.controller;

import com.cicidi.home.domain.repository.AccountRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.ProfileVo;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;
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
    ConnectionRepository connectionRepository;

    // spring data rest use profile as data profile
    @GetMapping("/resumeProfile")
    String resumeProfile(Model model, HttpServletRequest request, Principal principal) throws Exception {

        Profile profile = entityService.loadAndUpdate();
//        model.addAttribute("now", LocalDateTime.now());

        ProfileVo profileVo = new ProfileVo(profile);
//        profileVo.setFeature(this.createFeature());
        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(profile));
//        model.addAttribute("homeViewObject", this.createHomeViewObject());
//        model.addAttribute("profile", profile);
        model.addAttribute("profileVo", profileVo);
//        model.addAttribute("webLogList", gitHubService.createLog());
//        model.addAttribute("geoData", mapper.writeValueAsString(googleMapService.getGeoData(profile)));
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
        profileVo.setWebLogList(gitHubService.createLog());
        profileVo.setPlaces(googleMapService.getPlaces(linkedInProfileFull));
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

//    private HomeViewObject createHomeViewObject() {
//        HomeViewObject homeViewObject = new HomeViewObject();
//        homeViewObject.setFeature(createFeature());
//        return homeViewObject;
//    }

//    private Feature createFeature() {
//        // owlCarousel
//        Feature feature = new Feature();
//        List<Item> itemList = new ArrayList<>();
//        Item item_1 = new Item();
//        item_1.setTitle("hard working");
//        item_1.setSubTitle("refactory N engineer work in  half time");
//        item_1.setImgSrc(Constants.icon_1);
//        Item item_2 = new Item();
//        item_2.setTitle("quick learner");
//        item_2.setSubTitle("learn hadoop and python");
//        item_2.setImgSrc(Constants.icon_2);
//        Item item_3 = new Item();
//        item_3.setTitle("motivate");
//        item_3.setSubTitle("Learning Python and android by myself");
//        item_3.setImgSrc(Constants.icon_3);
//        Item item_4 = new Item();
//        item_4.setTitle("easy going");
//        item_4.setSubTitle("Play soccer / team work");
//        item_4.setImgSrc(Constants.icon_4);
//
//        itemList.add(item_1);
//        itemList.add(item_2);
//        itemList.add(item_3);
//        itemList.add(item_4);
//        feature.setItemList(itemList);
//        return feature;
//    }


    @ModelAttribute("adminMenu")
    public Collection<String> getAdminMenu(HttpSession session, HttpServletRequest request) {
        session.setAttribute("adminMenu", "something");
        return null;
    }


    private final Provider<ConnectionRepository> connectionRepositoryProvider;

    private final AccountRepository accountRepository;

    @Inject
    public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, AccountRepository accountRepository) {
        this.connectionRepositoryProvider = connectionRepositoryProvider;
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/home")
    public String home(Principal currentUser, Model model) {
        model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
        if (currentUser != null) {
            model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
        }
        return "home";
    }

    private ConnectionRepository getConnectionRepository() {
        return connectionRepositoryProvider.get();
    }
}
