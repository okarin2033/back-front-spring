package com.example.demo.Dao;

import com.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRep extends JpaRepository<Users, Long> {
    @Transactional
    public void deleteUsersByName(String name);
    public Users getUsersByName(String name);
}
