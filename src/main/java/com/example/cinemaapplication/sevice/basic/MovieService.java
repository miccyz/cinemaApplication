package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void remove(Movie movie) {
        movieRepository.delete(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) throws MovieNotFound {
        return movieRepository.findById(id).orElseThrow(MovieNotFound::new);
    }
}
