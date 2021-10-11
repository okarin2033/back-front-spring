package com.front.end.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {
    Long id;
    String name;
    String phone;


    @JsonSerialize(as = PrivDto.class)
    PrivDto priv;

    @Override
    public String toString() {
        return name;
    }


}
