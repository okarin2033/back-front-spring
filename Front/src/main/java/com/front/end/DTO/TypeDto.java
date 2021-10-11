package com.front.end.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {
    long typeId;
    String name;
    String descr;

    @Override
    public String toString() {
        return name;
    }
}
