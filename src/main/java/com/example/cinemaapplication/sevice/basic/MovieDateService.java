package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.exception.movieDate.MovieDateNotFound;
import com.example.cinemaapplication.model.MovieDate;
import com.example.cinemaapplication.repository.MovieDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieDateService {

    private final MovieDateRepository movieDateRepository;

    public MovieDate save(MovieDate movieDate) {
        return movieDateRepository.save(movieDate);
    }

    public void remove(MovieDate movieDate) {
        movieDateRepository.delete(movieDate);
    }

    public List<MovieDate> findAll() {
        return movieDateRepository.findAll();
    }

    public MovieDate findById(Integer id) throws MovieDateNotFound {
        return movieDateRepository.findById(id).orElseThrow(MovieDateNotFound::new);
    }
}
