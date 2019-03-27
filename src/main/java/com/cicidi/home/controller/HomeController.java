package com.cicidi.home.controller;

import com.cicidi.home.domain.resume.Profile;
import com.cicidi.home.domain.vo.ProfileVo;
import com.cicidi.home.service.AccountService;
import com.cicidi.home.service.GitHubService;
import com.cicidi.home.service.GoogleMapService;
import com.cicidi.home.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
class HomeController implements IHomeService {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    GoogleMapService googleMapService;

    @Autowired
    GitHubService gitHubService;

    @Autowired
    ConnectionRepository connectionRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    AccountService accountService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();


    @Value("${tmpFolder}")
    private String tmpFolder;

    public
    // spring data rest use profile as data profile
    @GetMapping("/profile/{username}")
    String resumeProfile(Model model, @PathVariable String username) {
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
    public String resumeProfile(Model model, HttpServletRequest request) {
        return "redirect:/profile" + "/" + request.getRemoteUser();

    }

    @GetMapping({"/", "/home"})
    public String option(Model model, Principal principal, HttpServletRequest request) {
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
    @GetMapping("/deleteProfile/{username}")
    @ResponseBody
    public String delelteProfile(@PathVariable String username) {
        profileService.delete(username);
        return "200";
    }

    @GetMapping("/deleteAccount/{username}")
    @ResponseBody
    public String deletepAccount(@PathVariable String username) {
        accountService.deleteAccount(username);
        return "202";
    }

    @RequestMapping(value = "/downloadResume", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
//        try {
//            // get your file as InputStream
//            InputStream is = new FileInputStream(new File("/tmp//walter_chen_resume.pdf"));
//            // copy it to response's OutputStream
//            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
//            response.flushBuffer();
//        } catch (IOException ex) {
//            logger.info("can not read resume file: {}", ex);
//            throw new RuntimeException("IOError writing file to output stream");
//        }
//        String dataDirectory = "/tmp//walter_chen_resume.pdf";
        Path file = Paths.get(tmpFolder, "walter_chen_resume.pdf");
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + "walter_chen_resume.pdf");
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
