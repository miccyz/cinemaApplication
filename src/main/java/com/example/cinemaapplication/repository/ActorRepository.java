package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {

    List<Actor> findAll();
}
