package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.exception.cinema.CinemaNotFound;
import com.example.cinemaapplication.model.Cinema;
import com.example.cinemaapplication.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public Cinema save(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public void remove(Cinema cinema) {
        cinemaRepository.delete(cinema);
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findById(Integer id) throws CinemaNotFound {
        return cinemaRepository.findById(id).orElseThrow(CinemaNotFound::new);
    }
}
