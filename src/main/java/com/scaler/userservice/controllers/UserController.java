package com.scaler.userservice.controllers;

import com.scaler.userservice.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    public User login(){
    //check if email and pwd in db- if yes return user,if no through some error

        return null;
    }

    public User signUp(){
    //No need to hash password for now, just save the user in db
    //    for now no need email verification
        return null;
    }

    public ResponseEntity<Void> logout(){
    //delete token if exists->200
    //if doesn't exist give a 404
        return null;
    }
}
