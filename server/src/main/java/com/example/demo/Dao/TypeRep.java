package com.example.demo.Dao;

import com.example.demo.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRep extends JpaRepository<Type, Long> {
    public Type getTypeByName(String name);
}
