package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue
    Long id;
    String date;
    @ManyToOne
    Employee employee;
    @ManyToOne
    Users user;
    @OneToOne
    Payment payment;

    @OneToMany
    List<Item> itemList= new ArrayList<Item>();

    public Orders(String date) {
        this.date=date;

    }

    public void addItem(Item item){
        itemList.add(item);
    }
}
