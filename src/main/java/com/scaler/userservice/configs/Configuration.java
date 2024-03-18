package com.scaler.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//we create Bean when we need only 1 object of a particular class throughout the application
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    //Here 16 refers to the round of encryption
    //The higher the number, the more secure the password is,but it will take more time to encrypt
    //but encoding is only once during login, later tokens are used for authentication
}
/*
use of Configuration class in Spring Boot?
->Configuration class is used to define the beans that can be autowired at different places
 in the application.

@Bean annotation in Spring Boot?
->@Bean annotation is used to define the beans that can be autowired at different places in the
 application.
i.e. object of BCryptPasswordEncoder() can be autowired at different places in the application

Autowire
 means to inject the object dependency implicitly. It internally uses setter or constructor injection.
 It is better for loose coupling because no need to write the extra code to inject the dependency.


if i have to clear custom beans that can autowire at different places, i just create them in config class
or
when i want spring to be able to create one object of that class, store it in its Application context
and be able to autowire it at different places in the application
*/