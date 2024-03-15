package com.scaler.userservice.dtos;
import lombok.Getter;
import lombok.Setter;


//Data Transfer Object - an object I will get from outside / I will send outside
@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;

}
