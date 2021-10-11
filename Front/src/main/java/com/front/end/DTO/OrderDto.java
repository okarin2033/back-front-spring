package com.front.end.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    long id;
    String date;
    EmpDto employee;
    UsersDto user;
    PaymentDto payment;
    ArrayList<ItemDto> itemList;

    @Override
    public String toString() {
        return date +" -- "+ user ;
    }
}
