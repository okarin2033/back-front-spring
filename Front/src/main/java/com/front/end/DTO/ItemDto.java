package com.front.end.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    long itemId;
    String name;
    TypeDto type;

    @Override
    public String toString() {
        return name;
    }
}
