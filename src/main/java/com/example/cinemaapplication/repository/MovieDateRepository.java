package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.MovieDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDateRepository extends CrudRepository<MovieDate, Integer> {

    List<MovieDate> findAll();
}
