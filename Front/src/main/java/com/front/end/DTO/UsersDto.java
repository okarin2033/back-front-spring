package com.front.end.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    long userId;
    String name;
    String email;
    String phone;
    @JsonIgnore
    public static int filter;
    public static void resetFilter(){
        filter=0;
    }
    @Override
    public String toString() {
        if (filter==0)
            return name;
        else if (filter==1)
            return phone;
        else if (filter==2)
            return email;
        else return name;
    }
}
