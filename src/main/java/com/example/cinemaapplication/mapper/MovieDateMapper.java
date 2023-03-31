package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.movieDate.MovieDateDto;
import com.example.cinemaapplication.model.MovieDate;
import org.springframework.stereotype.Service;

@Service
public class MovieDateMapper {

    public MovieDateDto toDto(MovieDate movieDate) {
        return new MovieDateDto(
                movieDate.getId(),
                movieDate.getDate(),
                movieDate.getStartTime(),
                movieDate.getEndTime()
        );
    }
}
