package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Integer> {

    List<Cinema> findAll();
}
