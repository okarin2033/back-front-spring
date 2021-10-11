package com.example.demo.Controller;

import com.example.demo.Dao.UserRep;
import com.example.demo.Dto.TextDto;
import com.example.demo.Dto.UsersDto;
import com.example.demo.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    UserRep userRep;
    @PostMapping("/add")
    public void addUser(@RequestBody UsersDto usersDto){
        Users users= new Users(usersDto.getName(),usersDto.getEmail(), usersDto.getPhone());
        userRep.save(users);
        System.out.println(users);
    }

    @GetMapping("/addb")
    public Long addUser(){
        Users users= new Users();
        userRep.save(users);
        return users.getUserId();
    }


    @PostMapping("/delete")
    public void deleteUser(@RequestBody TextDto textDto){
        System.out.println(textDto.getText());
        userRep.deleteById((long) Integer.parseInt(textDto.getText()));
    }
    @GetMapping("/getlist")
    public List<Users> getUsers(){
        return userRep.findAll();
    }

    @PostMapping("/upd")
    public void updUser(@RequestBody UsersDto usersDto){
        Users user = userRep.getById((long) usersDto.getUserId());

        System.out.println(user);
        System.out.println();
        System.out.println(usersDto);
        user.setName(usersDto.getName());
        user.setEmail(usersDto.getEmail());
        user.setPhone(usersDto.getPhone());
        userRep.save(user);
    }

}

