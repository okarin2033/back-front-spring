package com.example.demo.Dao;

import com.example.demo.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRep extends JpaRepository<Payment, Long> {
 //   public Payment getPaymentByPaymentType(String paymentType);
}
