package com.example.easynotes.controller;

import com.example.easynotes.model.User;
import com.example.easynotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Created by JS.
 */

@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Show all users
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    // JUST FOR TESTING THE PRINCIPAL
    @GetMapping("/current")
    public String getCurrent(Principal principal){
        return principal.getName();
    }
}