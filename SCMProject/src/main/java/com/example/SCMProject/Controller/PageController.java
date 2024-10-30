package com.example.SCMProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SCMProject.Entities.User;
import com.example.SCMProject.Form.UserForm;
import com.example.SCMProject.Services.UserService;
import com.example.SCMProject.Utilities.Message;
import com.example.SCMProject.Utilities.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class PageController {

    
@Autowired
private UserService userService;


@RequestMapping("/")
public String index(){
    return "redirect:/home";
} 

@RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Test Controller Method");
        model.addAttribute("name", "Sara Tasleem");
        model.addAttribute("Organization","Infosys Ltd.");
        model.addAttribute("GithubUrl", "https://github.com/saratasleem/SCMProject.git");
        return "home";
    }
@RequestMapping("/about")
    public String aboutPage(Model model){
         model.addAttribute("isLogin", false);
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        return "services";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("/register")
    public String registerPage(Model model){
        UserForm userForm=new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping("/contact")
    public String contactusPage(){
        return "contact";
    }
    @RequestMapping(value="/do-register",method=RequestMethod.POST)
    public String processingRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing registeration");
        System.out.println(userForm);
        if(rBindingResult.hasErrors()){
            return "register";
        }
        User user= new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://in.images.search.yahoo.com/images/view;_ylt=AwrKC_2wURZnU8ov.j.9HAx.;_ylu=c2VjA3NyBHNsawNpbWcEb2lkAzIwNzI2MDJjYzI4NTllM2M4NmQ4MGE1MGExNmZiOThiBGdwb3MDMTAEaXQDYmluZw--?back=https%3A%2F%2Fin.images.search.yahoo.com%2Fsearch%2Fimages%3Fp%3Ddefault%2Bprofile%2Bpic%26type%3DE211IN826G0%26fr%3Dmcafee%26fr2%3Dpiv-web%26tab%3Dorganic%26ri%3D10&w=474&h=474&imgurl=i.imgflip.com%2F6yvpkj.jpg&rurl=https%3A%2F%2Far.inspiredpencil.com%2Fpictures-2023%2Ffacebook-default-profile-photo&size=4KB&p=default+profile+pic&oid=2072602cc2859e3c86d80a50a16fb98b&fr2=piv-web&fr=mcafee&tt=Facebook+Default+Profile+Photo&b=0&ni=21&no=10&ts=&tab=organic&sigr=u6QN01RdHBO6&sigb=gX_7E1nRPLzX&sigi=XC66e.WUXkwm&sigt=lfs_ruWixies&.crumb=Hdf2XGGsDIu&fr=mcafee&fr2=piv-web&type=E211IN826G0");
        User savedUser=userService.saveUser(user);
        System.out.println("user saved");
        Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);
        return "redirect:/register";
    }


}
