package com.example.cinemaapplication.dto.movieDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDateDto {

    private int id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
