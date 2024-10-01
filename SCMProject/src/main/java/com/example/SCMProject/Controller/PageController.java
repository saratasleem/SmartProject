package com.example.SCMProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
@RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Test Controller Method");
        model.addAttribute("name", "Sara Tasleem");
        model.addAttribute("Organization","Infosys Ltd.");
        model.addAttribute("GithubUrl", "https://github.com/saratasleem/SCMProject.git");
        return "home";
    }

}
