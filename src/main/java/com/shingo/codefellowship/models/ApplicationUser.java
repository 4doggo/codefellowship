package com.shingo.codefellowship.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    String firstName;
    String lastName;
    String dateOfBirth;
    String bio;

    @OneToMany(mappedBy = "user")
    List<Post> posts;

    @ManyToMany
            @JoinTable(
                    name = "user_posts",
                    joinColumns = { @JoinColumn(name = "doer_id")},
                    inverseJoinColumns = { @JoinColumn(name = "receiver_id")}
            )
    List<ApplicationUser> userIFollow;


    @ManyToMany
    List<ApplicationUser> userFollowingMe;


//    Default constructor
    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public List<Post>getPosts(){
        return this.posts;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public List<ApplicationUser> getUserIFollow(){
        return this.userIFollow;
    }

    public List<ApplicationUser> getUserFollowingMe(){
        return this.userFollowingMe;
    }

}
