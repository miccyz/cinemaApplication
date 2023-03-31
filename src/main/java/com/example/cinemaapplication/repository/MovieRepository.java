package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findAll();
}
