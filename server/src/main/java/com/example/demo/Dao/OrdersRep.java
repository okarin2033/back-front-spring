package com.example.demo.Dao;

import com.example.demo.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRep extends JpaRepository<Orders, Long> {

}
