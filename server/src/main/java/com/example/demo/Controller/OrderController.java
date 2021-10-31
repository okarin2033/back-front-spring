package com.example.demo.Controller;

import com.example.demo.Dao.*;
import com.example.demo.Dto.ItemDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.Dto.TextDto;
import com.example.demo.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrdersRep ordersRep;
    @Autowired
    UserRep userRep;
    @Autowired
    ItemRep itemRep;
    @Autowired
    PaymentRep paymentRep;
    @Autowired
    EmpRep empRep;
    @PostMapping("/upd")
    public void updOrder(@RequestBody OrderDto orderDto) {
        System.out.println(orderDto.toString());
        Orders orders = ordersRep.getById(orderDto.getId());
       // orders.setPayment(paymentRep.getById(orderDto.getPayment().getId()));
        orders.setPayment(paymentRep.getById(1L));
        orders.setUser(userRep.getById((long) orderDto.getUser().getUserId()));
        orders.setEmployee(empRep.getById(orderDto.getEmployee().getId()));
        orders.setDate(orderDto.getDate());
        orders.getItemList().clear();
        System.out.println(orderDto.getItemList());
        for (ItemDto item: orderDto.getItemList()){
            Item bdItem = itemRep.getById(item.getItemId());
            orders.getItemList().add(bdItem);
            System.out.println(bdItem);
        }
        ordersRep.save(orders);
    }
    @GetMapping("/addb")
    public String addOrderb(){
        Orders order = new Orders();
        ordersRep.save(order);
        return order.getId()+"";
    }

    @PostMapping("/del")
    public void deleteOrder(@RequestBody TextDto textDto){
        ordersRep.deleteById((long)Integer.parseInt(textDto.getText()));
    }

    @GetMapping("/getlist")
    public List<Orders> getOrdersList(){
        return ordersRep.findAll();
    }

}
