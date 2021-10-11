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

    @Override
    public String toString() {
        return name;
    }
}
