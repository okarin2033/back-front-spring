package com.example.demo.Dao;

import com.example.demo.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRep extends JpaRepository<Item, Long> {
    @Transactional
    public void deleteByName(String name);
    Item getItemByName(String name);

}
