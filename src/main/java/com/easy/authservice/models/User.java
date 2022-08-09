package com.easy.authservice.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseModel{

    private String username;
    private String password;

    public User() {
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return password;
    }
}
