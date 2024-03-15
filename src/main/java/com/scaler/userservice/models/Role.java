package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends BaseModel{
    private String name;
}

/*
Cardinality between User and Role
-> 1 user can have multiple roles
-> 1 role can be assigned to multiple users
-> s0 cardinality is many to many

 */