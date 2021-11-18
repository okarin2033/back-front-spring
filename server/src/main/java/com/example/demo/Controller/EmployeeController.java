package com.example.demo.Controller;

import com.example.demo.Dao.EmpRep;
import com.example.demo.Dao.PrivRep;
import com.example.demo.Dto.EmpDto;
import com.example.demo.Dto.TextDto;
import com.example.demo.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmpRep empRep;
    @Autowired
    PrivRep privRep;
    @PostMapping("/add")
    public void addEmp(@RequestBody EmpDto empDto){
        Employee employee= new Employee(empDto.getName(), empDto.getPassword());
        empRep.save(employee);
        System.out.println();
    }
    @GetMapping("/addb")
    public String addEmp(){
        Employee employee = new Employee();
        empRep.save(employee);
        return employee.getId()+"";
    }
    @PostMapping("/delete")
    public void deleteEmp(@RequestBody TextDto textDto) {
        System.out.println(textDto.getText());
        empRep.deleteById((long) Integer.parseInt(textDto.getText()));
    }

    @GetMapping("/getlist")
    public List<Employee> getEmpList(){
        return empRep.findAll();
    }

    @PostMapping("/upd")
    public void updEmp(@RequestBody EmpDto empDto){
        Employee employee = empRep.getById((long) empDto.getId());

        employee.setName(empDto.getName());
        employee.setPriv(privRep.getById(empDto.getPriv().getId()));
        employee.setPassword(empDto.getPassword());
        empRep.save(employee);
    }
}

