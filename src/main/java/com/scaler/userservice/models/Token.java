package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value;

    @ManyToOne
    private User user;
    private Date expiryAt;
}

/*
Cardinality between User and Token
1 Token will be for 1 user
1 user can have how many tokens?
-> Depends upon the number of devices/if we want to allow multiple logins
-> 1 user can have multiple tokens

sp cardinality is 1 to many

*/