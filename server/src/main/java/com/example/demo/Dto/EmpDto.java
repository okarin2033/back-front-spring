package com.example.demo.Dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpDto {
    long id;
    @NotNull
    String name;
    String password;
    PrivDto priv;
}
