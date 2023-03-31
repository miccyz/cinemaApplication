package com.example.cinemaapplication.repository;

import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    List<User> findAllByRole(UserRole role);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
