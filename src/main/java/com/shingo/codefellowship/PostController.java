package com.shingo.codefellowship;

import com.shingo.codefellowship.models.ApplicationUser;
import com.shingo.codefellowship.models.ApplicationUserRepository;
import com.shingo.codefellowship.models.Post;
import com.shingo.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/")
    public RedirectView createPost(String body, String title, int createdAt, Principal p){
        Post post = new Post(body, title, createdAt, applicationUserRepository.findByUsername(p.getName()));

        postRepository.save(post);
        return new RedirectView("/");
    }



}
