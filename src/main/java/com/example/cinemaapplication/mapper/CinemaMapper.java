package com.example.cinemaapplication.mapper;

import com.example.cinemaapplication.dto.cinema.CinemaAddDto;
import com.example.cinemaapplication.dto.cinema.CinemaDto;
import com.example.cinemaapplication.dto.cinema.CinemaEditDto;
import com.example.cinemaapplication.model.Cinema;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CinemaMapper {

    public CinemaDto toDto(Cinema cinema) {
        return new CinemaDto(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress()
        );
    }

    public Cinema toNewModel(CinemaAddDto addDto) {
        return new Cinema(
                null,
                addDto.getName(),
                addDto.getAddress(),
                Collections.emptyList()
        );
    }

    public CinemaEditDto toEditDto(Cinema cinema) {
        return new CinemaEditDto(
                cinema.getId(),
                cinema.getName(),
                cinema.getAddress()
        );
    }

    public Cinema toEditModel(Cinema cinema, CinemaEditDto editDto) {
        cinema.setName(editDto.getName());
        cinema.setAddress(editDto.getAddress());
        return cinema;
    }
}
