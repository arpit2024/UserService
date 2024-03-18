package com.scaler.userservice.dtos;

import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> role;
    private boolean isEmailVerified;

    public static UserDto from(User user) {

        //By using this if-loop we can handle the case when the user is null.(Bug Cleared)
        //explained in Controller class-ValidateToken method
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.role = user.getRole();
        userDto.isEmailVerified = user.isEmailVerified();

        return userDto;
    }
}


/*Reason For this Class

so while implementing the validation of token, we need to return the user details if the token is valid.
but in our code we were directly returning the User class object from the service method.
Controller method before changes: ->
            public User validateToken(@PathVariable("token") @NonNull String token) {
                   return userService.validateToken(token);
            }
o/p:
            {
                "id": 1,
                "deleted": false,
                "name": "Arpit",
                "email": "arpit@scaler.com",
                "hashedPassword": "$2a$12$svY0ETnCs6i5rWh2IUTvfOOiC5W73e/p95lSGas4eH/SuJu2Fg1RG",
                "role": [],
                "emailVerified": false
            }
but we should not return the User class object directly from the service method,
instead we should create a DTO class and return the object of that DTO class from the service method.

Advantage->
we can hide/not write the fields which we don't want to expose to the client.
ex - we don't want to expose the hashed password of the user to the client.
so we can hide that field in the DTO class.

After changes: ->
            public UserDto validateToken(@PathVariable("token") @NonNull String token) {
                return UserDto.from(userService.validateToken(token));
            }
O/P-
            {
                "name": "shreeyad",
                "email": "shreeyad@gmail.com",
                "role": [],
                "emailVerified": false
            }

 */