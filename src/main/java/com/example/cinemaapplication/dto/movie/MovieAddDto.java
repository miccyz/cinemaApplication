package com.example.cinemaapplication.dto.movie;

import com.example.cinemaapplication.model.MovieType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieAddDto {

    private String title;
    private String releaseDate;
    private MovieType type;
    private int durationInMinutes;

    private int cinemaId;
    private int stageManagerId;
}
