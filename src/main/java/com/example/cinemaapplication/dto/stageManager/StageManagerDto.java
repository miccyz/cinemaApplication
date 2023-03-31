package com.example.cinemaapplication.dto.stageManager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StageManagerDto {

    private int id;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
