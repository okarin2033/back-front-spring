package com.example.demo.Entity;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String password;

    public Employee(String name, String password) {
        this.name=name;
        this.password=password;
    }
    @ManyToOne
    Priv priv;
}
