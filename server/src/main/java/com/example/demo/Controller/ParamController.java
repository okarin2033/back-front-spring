package com.example.demo.Controller;

import com.example.demo.Dao.PaymentRep;
import com.example.demo.Dao.PrivRep;
import com.example.demo.Dao.TypeRep;
import com.example.demo.Entity.Payment;
import com.example.demo.Entity.Priv;
import com.example.demo.Entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParamController {
    @Autowired
    TypeRep typeRep;
    @Autowired
    PaymentRep paymentRep;
    @Autowired
    PrivRep privRep;
    @GetMapping("/type")
    public List<Type> getTypeList(){
        return typeRep.findAll();
    }
    @GetMapping("/payment")
    public List<Payment> getPaymentList(){
        return  paymentRep.findAll();
    }
    @GetMapping("/role")
    public List<Priv> getPrivList() {
        System.out.println(privRep.findAll());
        return privRep.findAll();
    }

}
