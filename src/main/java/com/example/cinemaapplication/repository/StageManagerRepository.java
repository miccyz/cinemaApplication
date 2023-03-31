package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.StageManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageManagerRepository extends CrudRepository<StageManager, Integer> {

    List<StageManager> findAll();
}
