package com.shingo.codefellowship;

import com.shingo.codefellowship.models.ApplicationUser;
import com.shingo.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getHome(Principal p, Model m){
        if(p != null){
            System.out.println(p.getName());
            m.addAttribute("username",p.getName());
            ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("user",user);

        }else {
            System.out.println("nobody logged in");
        }
        return "home";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        if(applicationUserRepository.findByUsername(username) == null){
            ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,bio);
            applicationUserRepository.save(newUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new RedirectView("/");
        } else{
            return new RedirectView("/signup?taken=true");
        }
    }



}
