package com.cicidi.home.controller;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.Feature;
import com.cicidi.home.domain.vo.HomeViewObject;
import com.cicidi.home.domain.vo.Item;
import com.cicidi.home.service.EntityService;
import com.cicidi.home.service.GitHubService;
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
    GoogleMapService googleMapService;

    @Autowired
    GitHubService gitHubService;
    @Autowired
    EntityService entityService;
    ObjectMapper mapper = new ObjectMapper();


    @GetMapping("/home")
    String home(Model model, HttpServletRequest request) throws Exception {

        Profile profile = entityService.loadAndUpdate();
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("homeViewObject", this.createHomeViewObject());
        model.addAttribute("profile", profile);
        model.addAttribute("webLogList", gitHubService.createLog());
        model.addAttribute("geoData", mapper.writeValueAsString(googleMapService.getGeoData(profile)));
        return "home";

    }

    @GetMapping("/test")
    @ResponseBody
    Profile test() throws JAXBException {
        Profile profile = entityService.loadAndUpdate();
        return profile;

    }

    private HomeViewObject createHomeViewObject() {
        HomeViewObject homeViewObject = new HomeViewObject();
        homeViewObject.setFeature(createFeature());
        return homeViewObject;
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


    @ModelAttribute("adminMenu")
    public Collection<String> getAdminMenu(HttpSession session, HttpServletRequest request) {
        session.setAttribute("adminMenu", "something");
        return null;
    }
}
