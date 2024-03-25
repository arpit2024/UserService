package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String email;
    private String hashedPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;
    private boolean isEmailVerified;
}
//id
// email
//username
//pwd
//phone

/*name-class{
    first name
    lastname
}
*/

/*address{
    city
    street
    number
    zipcode
    geolocation{
        lat
        long
    }
}
*/

