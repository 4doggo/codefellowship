package com.shingo.codefellowship;

import com.shingo.codefellowship.models.ApplicationUser;
import com.shingo.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

}
