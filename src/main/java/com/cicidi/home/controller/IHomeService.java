package com.cicidi.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public interface IHomeService {

    @GetMapping("/profiles")
    String resumeProfile(Model model, HttpServletRequest request);

    @GetMapping("/profile/{username}")
    String resumeProfile(Model model, @PathVariable String username);

    @GetMapping({"/", "/home"})
    String option(Model model, Principal principal, HttpServletRequest request);

    //only use for test purpose
    @GetMapping("/deleteProfile/{username}")
    @ResponseBody
    String delelteProfile(@PathVariable String username);

    @GetMapping("/deleteAccount/{username}")
    @ResponseBody
    String deletepAccount(@PathVariable String username);

    @RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
    void getFile(HttpServletResponse response);


}
