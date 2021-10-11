package com.example.demo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue
    private Long typeId;
    String name;
    String descr;

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
