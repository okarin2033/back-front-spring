package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Priv {
@Id
@GeneratedValue
Long id;
int access;
String descr;

    @Override
    public String toString() {
        return "Priv{" +
                "empId=" + id +
                ", access=" + access +
                ", descr='" + descr + '\'' +
                '}';
    }
}
