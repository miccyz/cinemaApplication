package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.movie.MovieAddDto;
import com.example.cinemaapplication.dto.movie.MovieEditDto;
import com.example.cinemaapplication.model.Cinema;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.model.StageManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class MovieMapper {

    public Movie toNewModel(MovieAddDto addDto, Cinema cinema, StageManager stageManager) {
        return new Movie(
                null,
                addDto.getTitle(),
                LocalDate.parse(addDto.getReleaseDate()),
                addDto.getType(),
                addDto.getDurationInMinutes(),
                cinema,
                Collections.emptyList(),
                stageManager,
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public MovieEditDto toEditDto(Movie movie) {
        return new MovieEditDto(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseDate().toString(),
                movie.getType(),
                movie.getDurationInMinutes(),
                movie.getCinema().getId(),
                movie.getStageManager().getId()
        );
    }

    public Movie toEditModel(Movie movie, MovieEditDto editDto, Cinema cinema, StageManager stageManager) {
        movie.setTitle(editDto.getTitle());
        movie.setReleaseDate(LocalDate.parse(editDto.getReleaseDate()));
        movie.setType(editDto.getType());
        movie.setDurationInMinutes(editDto.getDurationInMinutes());
        movie.setCinema(cinema);
        movie.setStageManager(stageManager);
        return movie;
    }
}
