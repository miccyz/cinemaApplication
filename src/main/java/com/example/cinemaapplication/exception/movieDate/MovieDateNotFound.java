package com.example.cinemaapplication.exception.movieDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieDateNotFound extends Exception {
}
