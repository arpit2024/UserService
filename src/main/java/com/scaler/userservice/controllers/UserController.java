package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.LoginRequestDto;
import com.scaler.userservice.dtos.LogoutRequestDto;
import com.scaler.userservice.dtos.SignUpRequestDto;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    //@Autowired-this keyword is optional in controller
    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request){
    //check if email and pwd in db- if yes return user,if no through some error
        return userService.login(request.getEmail(), request.getPassword());
        //return null;
    }


    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequestDto requestDto){
    //No need to hash password for now, just save the user in db
    //    for now no need email verification
        String name=requestDto.getName();
        String email=requestDto.getEmail();
        String password=requestDto.getPassword();
        //return null;
        return userService.SignUp(name,email,password);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){
    //delete token if exists->200
    //if doesn't exist give a 404
        userService.logout(request.getToken());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


// Controllers take care of the incoming requests and send the response back to the client.
//Dto are used in controllers to get the data from the client and send the data to the client.
//Not in Service class