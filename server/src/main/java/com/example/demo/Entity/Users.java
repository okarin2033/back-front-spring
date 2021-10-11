package com.example.demo.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue
    private Long UserId;
    String name;
    String email;
    String phone;


    public Users(String name, String email, String phone) {
        this.name=name;
        this.email=email;
        this.phone=phone;
    }
}
