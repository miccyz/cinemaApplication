package com.example.cinemaapplication.dto.actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActorDto {

    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
