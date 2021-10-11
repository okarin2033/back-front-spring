package com.example.demo.Dao;

import com.example.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpRep extends JpaRepository<Employee, Long> {
@Transactional
    void deleteByName(String name);
    Employee getEmployeesByName(String name);
}
