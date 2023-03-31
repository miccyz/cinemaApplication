package com.example.cinemaapplication.dto.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorEditDto {

    private int id;
    private String firstName;
    private String lastName;
}
