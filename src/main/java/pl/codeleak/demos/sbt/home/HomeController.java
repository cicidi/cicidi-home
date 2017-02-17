package pl.codeleak.demos.sbt.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.codeleak.demos.sbt.domain.HomeViewObject;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
class HomeController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }

    @GetMapping("/home")
    String home(Model model, HttpServletRequest request) {
        HomeViewObject homeViewObject = new HomeViewObject();
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("homeViewObject", homeViewObject);
        System.out.println(request.getRemoteAddr());
        return "home";
    }

    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }
}
