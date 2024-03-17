package com.scaler.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
}
// Dto is used to get the data from the client and send the data to the client.
