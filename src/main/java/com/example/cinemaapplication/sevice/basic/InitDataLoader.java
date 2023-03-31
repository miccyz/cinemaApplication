package com.example.cinemaapplication.sevice.basic;

import com.example.cinemaapplication.model.*;
import com.example.cinemaapplication.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitDataLoader {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    private final ActorRepository actorRepository;
    private final StageManagerRepository stageManagerRepository;
    private final MovieRepository movieRepository;
    private final MovieDateRepository movieDateRepository;

    @PostConstruct
    public void initialize() {
        initializeUser();
        List<Cinema> cinemaList = initializeCinema();
        List<Actor> actorList = initializeActor();
        List<StageManager> stageManagerList = initializeStageManager();
        List<Movie> movieList = initializeMovie(cinemaList.get(0), stageManagerList.get(0), actorList);

        initializeMovieDate(movieList.get(0));
        initializeMovieDate(movieList.get(1));
    }

    public void initializeUser() {
        User user = new User(null, "aaa", "aaa", "a@a", "aaa", true, UserRole.ADMIN, Collections.emptyList());
        userRepository.save(user);

        user = new User(null, "bbb", "bbb", "b@b", "bbb", true, UserRole.USER, Collections.emptyList());
        userRepository.save(user);

        user = new User(null, "ccc", "ccc", "c@c", "ccc", true, UserRole.USER, Collections.emptyList());
        userRepository.save(user);
    }

    public List<Cinema> initializeCinema() {
        List<Cinema> cinemaList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Cinema cinema = new Cinema(null, "cinema " + i, "address " + i, Collections.emptyList());
            cinemaRepository.save(cinema);
            cinemaList.add(cinema);
        }

        return cinemaList;
    }

    public List<Actor> initializeActor() {
        List<Actor> actorList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Actor actor = new Actor(null, "actor " + i, "actor " + i, Collections.emptyList());
            actorRepository.save(actor);
            actorList.add(actor);
        }

        return actorList;
    }

    public List<StageManager> initializeStageManager() {
        List<StageManager> stageManagerList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            StageManager stageManager = new StageManager(null, "stageManager " + i, "stageManager " + i, Collections.emptyList());
            stageManagerRepository.save(stageManager);
            stageManagerList.add(stageManager);
        }

        return stageManagerList;
    }

    private List<Movie> initializeMovie(Cinema cinema, StageManager stageManager, List<Actor> actorList) {
        LocalDate now = LocalDate.now();
        List<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Movie movie = new Movie(null, "title " + i, now, MovieType.ACTION, 90, cinema, Collections.emptyList(), stageManager, actorList, Collections.emptyList());
            movieRepository.save(movie);
            movieList.add(movie);
        }

        return movieList;
    }

    private void initializeMovieDate(Movie movie) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        for (int i = 0; i < 2; i++) {
            MovieDate movieDate = new MovieDate(null, localDate, localTime, localTime.plusMinutes(movie.getDurationInMinutes()), movie);
            movieDateRepository.save(movieDate);
        }
    }
}
