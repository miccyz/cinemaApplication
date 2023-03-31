package com.example.cinemaapplication.sevice.user;

import com.example.cinemaapplication.dto.actor.ActorDto;
import com.example.cinemaapplication.dto.cinema.CinemaDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerDto;
import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.exception.user.UserNotFound;
import com.example.cinemaapplication.mapper.ActorMapper;
import com.example.cinemaapplication.mapper.CinemaMapper;
import com.example.cinemaapplication.mapper.StageManagerMapper;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.model.MovieType;
import com.example.cinemaapplication.model.User;
import com.example.cinemaapplication.sevice.basic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("userMovieSFC")
@RequiredArgsConstructor
public class MovieSFC {

    private final MovieService movieService;
    private final UserService userService;
    private final CinemaService cinemaService;
    private final ActorService actorService;
    private final StageManagerService stageManagerService;

    private final CinemaMapper cinemaMapper;
    private final ActorMapper actorMapper;
    private final StageManagerMapper stageManagerMapper;

    public ModelAndView displayList(HttpServletRequest request, String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) throws UserNotFound {
        List<CinemaDto> cinemaDtoList = cinemaService.findAll().stream().map(cinemaMapper::toDto).collect(Collectors.toList());
        List<ActorDto> actorDtoList = actorService.findAll().stream().map(actorMapper::toDto).collect(Collectors.toList());
        List<StageManagerDto> stageManagerDtoList = stageManagerService.findAll().stream().map(stageManagerMapper::toDto).collect(Collectors.toList());
        MovieType[] movieTypes = MovieType.values();

        model.addAttribute("cinemaList", cinemaDtoList);
        model.addAttribute("actorList", actorDtoList);
        model.addAttribute("stageManagerList", stageManagerDtoList);
        model.addAttribute("movieTypes", movieTypes);
        setMovieListModel(request, title, releaseDate, movieDate, cinemaId, stageManagerId, actorId, movieType, model);
        setFilterModel(title, releaseDate, movieDate, cinemaId, stageManagerId, actorId, movieType, model);
        return new ModelAndView("user/movie/favorites/list");
    }

    private void setMovieListModel(HttpServletRequest request, String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) throws UserNotFound {
        User user = userService.findBySession(request);
        List<Movie> movieList = user.getMovieList();

        if (!title.isEmpty())
            movieList = movieList.stream().filter(e -> e.getTitle().equals(title)).collect(Collectors.toList());

        if (!releaseDate.isEmpty()) {
            movieList = movieList.stream().filter(e -> e.getReleaseDate().equals(LocalDate.parse(releaseDate))).collect(Collectors.toList());
        }

        if (!movieDate.isEmpty())
            movieList = movieList.stream().filter(e -> e.getMovieDateList().stream().anyMatch(j -> j.getDate().equals(LocalDate.parse(movieDate)))).collect(Collectors.toList());

        if (cinemaId >= 0)
            movieList = movieList.stream().filter(e -> e.getCinema().getId().equals(cinemaId)).collect(Collectors.toList());

        if (stageManagerId >= 0)
            movieList = movieList.stream().filter(e -> e.getStageManager().getId().equals(stageManagerId)).collect(Collectors.toList());

        if (actorId >= 0)
            movieList = movieList.stream().filter(e -> e.getActorList().stream().anyMatch(j -> j.getId().equals(actorId))).collect(Collectors.toList());

        if (movieType != MovieType.ALL)
            movieList = movieList.stream().filter(e -> e.getType().equals(movieType)).collect(Collectors.toList());

        model.addAttribute("movieList", movieList);
    }

    private void setFilterModel(String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("releaseDate", releaseDate);
        model.addAttribute("movieDate", movieDate);
        model.addAttribute("cinemaId", cinemaId);
        model.addAttribute("stageManagerId", stageManagerId);
        model.addAttribute("actorId", actorId);
        model.addAttribute("movieType", movieType);
    }

    public ModelAndView add(Integer movieId, HttpServletRequest request) throws UserNotFound, MovieNotFound {
        User user = userService.findBySession(request);
        Movie movie = movieService.findById(movieId);

        user.getMovieList().add(movie);
        userService.save(user);
        return new ModelAndView("redirect:/authFree/movie/list");
    }

    public ModelAndView remove(Integer movieId, HttpServletRequest request) throws MovieNotFound, UserNotFound {
        User user = userService.findBySession(request);
        Movie movie = movieService.findById(movieId);

        user.getMovieList().remove(movie);
        userService.save(user);
        return new ModelAndView("redirect:list");
    }
}
