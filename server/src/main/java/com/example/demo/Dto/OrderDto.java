package com.example.demo.Dto;

import com.example.demo.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    long id;
    String date;
    EmpDto employee;
    UsersDto user;
    PaymentDto payment;
    ArrayList<ItemDto> itemList;

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", employee=" + employee +
                ", user=" + user +
                ", payment=" + payment +
                ", itemList=" + itemList +
                '}';
    }
}
