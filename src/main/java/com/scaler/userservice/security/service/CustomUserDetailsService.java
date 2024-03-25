package com.scaler.userservice.security.service;

import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import com.scaler.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //here username is nothing but email
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User by email: " + username + " doesn't exist.");
        }
        //here return userDetails.get() will not work because userDetails is of type CustomUserDetails
        //and get() method is not available for this type. So we need to create an object of CustomUserDetails
        //and pass the userOptional.get() to it.
        CustomUserDetails userDetails = new CustomUserDetails(userOptional.get());

        return userDetails;
    }
}
/*
This class is responsible for generating the token for the user.
When we send a request to the user service,
we create a token by using the user service, so the user service is responsible for generating the token.
postman talks to the user service generate the user token, now we send request to product service to
by using this token.
So the product service will validate the token by using the user service.
note - Here user's role is played by Postman  */