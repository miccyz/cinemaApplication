package com.example.cinemaapplication.sevice.authFree;

import com.example.cinemaapplication.dto.actor.ActorDto;
import com.example.cinemaapplication.dto.cinema.CinemaDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerDto;
import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.mapper.ActorMapper;
import com.example.cinemaapplication.mapper.CinemaMapper;
import com.example.cinemaapplication.mapper.StageManagerMapper;
import com.example.cinemaapplication.model.Movie;
import com.example.cinemaapplication.model.MovieType;
import com.example.cinemaapplication.sevice.basic.ActorService;
import com.example.cinemaapplication.sevice.basic.CinemaService;
import com.example.cinemaapplication.sevice.basic.MovieService;
import com.example.cinemaapplication.sevice.basic.StageManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("authFreeMovieSFC")
@RequiredArgsConstructor
public class MovieSFC {

    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final ActorService actorService;
    private final StageManagerService stageManagerService;

    private final CinemaMapper cinemaMapper;
    private final ActorMapper actorMapper;
    private final StageManagerMapper stageManagerMapper;

    public ModelAndView displayList(String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) {
        List<CinemaDto> cinemaDtoList = cinemaService.findAll().stream().map(cinemaMapper::toDto).collect(Collectors.toList());
        List<ActorDto> actorDtoList = actorService.findAll().stream().map(actorMapper::toDto).collect(Collectors.toList());
        List<StageManagerDto> stageManagerDtoList = stageManagerService.findAll().stream().map(stageManagerMapper::toDto).collect(Collectors.toList());
        MovieType[] movieTypes = MovieType.values();

        model.addAttribute("cinemaList", cinemaDtoList);
        model.addAttribute("actorList", actorDtoList);
        model.addAttribute("stageManagerList", stageManagerDtoList);
        model.addAttribute("movieTypes", movieTypes);
        setMovieListModel(title, releaseDate, movieDate, cinemaId, stageManagerId, actorId, movieType, model);
        setFilterModel(title, releaseDate, movieDate, cinemaId, stageManagerId, actorId, movieType, model);
        return new ModelAndView("authFree/movie/list");
    }

    public ModelAndView displayDetails(Integer id, Model model) throws MovieNotFound {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return new ModelAndView("authFree/movie/details");
    }

    private void setMovieListModel(String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) {
        List<Movie> movieList = movieService.findAll();

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
}
