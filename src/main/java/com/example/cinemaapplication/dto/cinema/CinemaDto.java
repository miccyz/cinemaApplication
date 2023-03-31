package com.example.cinemaapplication.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CinemaDto {

    private int id;
    private String name;
    private String address;
}
