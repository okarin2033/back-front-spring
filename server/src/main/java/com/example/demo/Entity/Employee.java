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
    String phone;

    public Employee(String name, String phone) {
        this.name=name;
        this.phone=phone;
    }
    @ManyToOne
    Priv priv;
}
