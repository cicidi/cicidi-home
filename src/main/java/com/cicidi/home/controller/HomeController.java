package com.cicidi.home.controller;

import com.cicidi.home.domain.repository.ProfileRepository;
import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.*;
import com.cicidi.home.io.XMLReader;
import com.cicidi.home.service.GoogleMapService;
import com.cicidi.home.service.Test;
import com.cicidi.home.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
class HomeController {

    @Autowired
    Test test;

    @Autowired
    XMLReader xmlReader;

    @Autowired
    GoogleMapService googleMapService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ProfileRepository profileRepository;

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }

    @GetMapping("/home")
    String home(Model model, HttpServletRequest request) throws Exception {

        HomeViewObject homeViewObject = this.testCase();
        Profile profile = xmlReader.parseFile();
        profileRepository.save(profile);
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("homeViewObject", homeViewObject);
        model.addAttribute("profile", profile);
        model.addAttribute("objective", test.createObjective());
        model.addAttribute("webLogList", test.createLog());
        model.addAttribute("geoData", mapper.writeValueAsString(googleMapService.getGeoData(profile)));
        return "home";

    }

    @GetMapping("/test")
    @ResponseBody
    String test() throws JAXBException {
        Profile profile = xmlReader.parseFile();
        profileRepository.save(profile);
        return "200OK";

    }

    @GetMapping("/addMarker")
    @ResponseBody
    String addMarker(Model model, HttpServletRequest request) throws JAXBException {
        HomeViewObject homeViewObject = this.testCase();
        Profile profile = xmlReader.parseFile();
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("homeViewObject", homeViewObject);
        model.addAttribute("profile", profile);
        model.addAttribute("objective", test.createObjective());
        model.addAttribute("webLogList", test.createLog());
        System.out.println(request.getRemoteAddr());
        return "home";

    }


    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }

    private HomeViewObject testCase() {
        HomeViewObject homeViewObject = new HomeViewObject();


        homeViewObject.setOwlCarousel(this.createOwlCarousel());
        homeViewObject.setFeature(createFeature());
        homeViewObject.setObjective(createaObjective());
        return homeViewObject;

//

    }

    private OwlCarousel createOwlCarousel() {
        OwlCarousel owlCarousel = new OwlCarousel();
        List<Item> itemList = new ArrayList<>();
        Item item_1 = new Item();
        item_1.setHeader("CGI");
        item_1.setParagraph("work in cgi");
        item_1.setSrc(Constants.link_1);
        Item item_2 = new Item();
        item_2.setHeader("HUAWEI");
        item_2.setParagraph("Work in HUAWEI");
        item_2.setSrc(Constants.link_2);
        Item item_3 = new Item();
        item_3.setHeader("APPLE");
        item_3.setParagraph("Working in APPLE");
        item_3.setSrc(Constants.link_3);
        itemList.add(item_1);
        itemList.add(item_2);
        itemList.add(item_3);
        owlCarousel.setItemList(itemList);
        return owlCarousel;
    }


    private Feature createFeature() {
        // owlCarousel
        Feature feature = new Feature();
        List<Item> itemList = new ArrayList<>();
        Item item_1 = new Item();
        item_1.setHeader("hard working");
        item_1.setParagraph("refactory N engineer work in  half time");
        item_1.setSrc(Constants.icon_1);
        Item item_2 = new Item();
        item_2.setHeader("quick learner");
        item_2.setParagraph("learn hadoop and python");
        item_2.setSrc(Constants.icon_2);
        Item item_3 = new Item();
        item_3.setHeader("motivate");
        item_3.setParagraph("Learning Python and android by myself");
        item_3.setSrc(Constants.icon_3);
        Item item_4 = new Item();
        item_4.setHeader("easy going");
        item_4.setParagraph("Play soccer / team work");
        item_4.setSrc(Constants.icon_4);

        itemList.add(item_1);
        itemList.add(item_2);
        itemList.add(item_3);
        itemList.add(item_4);
        feature.setItemList(itemList);
        return feature;
    }

    private Objective createaObjective() {
        Objective objective = new Objective();
        objective.setTitle_1("I LOVE JAVA");
        objective.setParagraph_1("I am enjoy in my current job, but I am also looking for new challenge");
        objective.setTitle_2("I AM BACKEND DEVELOPER");
        objective.setParagraph_2("I can work on both front end and backend, but I prefer backend");
        objective.setContent("This web site is create using some of my coding skill, So it is easilier for you to check if I am a good fit");


        List<Link> links = new ArrayList<>();
        Link link_1 = new Link();
        link_1.setName("linkedin");
        link_1.setUrl("https://www.linkedin.com/");
        link_1.setIcon(Constants.icon_5);

        Link link_2 = new Link();
        link_2.setName("stackoverflow");
        link_2.setUrl("https://www.linkedin.com/");
        link_2.setIcon(Constants.icon_6);

        Link link_3 = new Link();
        link_3.setName("github");
        link_3.setUrl("https://github.com/cicidi/cicidi-home");
        link_3.setIcon(Constants.icon_7);
        links.add(link_1);
        links.add(link_2);
        links.add(link_3);
        objective.setLinks(links);
        objective.setImgSrc("img/about.jpg");
        return objective;
    }

    @ModelAttribute("adminMenu")
    public Collection<String> getAdminMenu(HttpSession session, HttpServletRequest request) {
        session.setAttribute("adminMenu", "something");

        return null;
    }
}
