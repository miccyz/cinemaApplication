package com.example.cinemaapplication.sevice.admin;

import com.example.cinemaapplication.dto.actor.ActorDto;
import com.example.cinemaapplication.dto.cinema.CinemaDto;
import com.example.cinemaapplication.dto.movie.MovieAddDto;
import com.example.cinemaapplication.dto.movie.MovieEditDto;
import com.example.cinemaapplication.dto.movieDate.MovieDateDto;
import com.example.cinemaapplication.dto.stageManager.StageManagerDto;
import com.example.cinemaapplication.exception.actor.ActorNotFound;
import com.example.cinemaapplication.exception.cinema.CinemaNotFound;
import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.exception.movieDate.MovieDateNotFound;
import com.example.cinemaapplication.exception.stageManager.StageManagerNotFound;
import com.example.cinemaapplication.mapper.*;
import com.example.cinemaapplication.model.*;
import com.example.cinemaapplication.sevice.basic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("adminMovieSFC")
@RequiredArgsConstructor
public class MovieSFC {

    private final MovieService movieService;
    private final CinemaService cinemaService;
    private final StageManagerService stageManagerService;
    private final MessageService messageService;
    private final ActorService actorService;
    private final MovieDateService movieDateService;

    private final MovieMapper movieMapper;
    private final CinemaMapper cinemaMapper;
    private final StageManagerMapper stageManagerMapper;
    private final ActorMapper actorMapper;
    private final MovieDateMapper movieDateMapper;

    public ModelAndView displayList(Model model) {
        List<Movie> movieList = movieService.findAll();
        model.addAttribute("movieList", movieList);
        return new ModelAndView("admin/movie/list");
    }

    public ModelAndView displayAddForm(Model model) {
        List<CinemaDto> cinemaList = cinemaService.findAll().stream().map(cinemaMapper::toDto).collect(Collectors.toList());
        List<StageManagerDto> stageManagerList = stageManagerService.findAll().stream().map(stageManagerMapper::toDto).collect(Collectors.toList());

        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("stageManagerList", stageManagerList);
        model.addAttribute("movieTypeList", MovieType.values());
        model.addAttribute("addDto", new MovieAddDto());
        return new ModelAndView("admin/movie/add");
    }

    public ModelAndView add(MovieAddDto addDto, Model model) throws CinemaNotFound, StageManagerNotFound {
        Cinema cinema = cinemaService.findById(addDto.getCinemaId());
        StageManager stageManager = stageManagerService.findById(addDto.getStageManagerId());
        Movie movie = movieMapper.toNewModel(addDto, cinema, stageManager);
        movieService.save(movie);
        messageService.responseMessage(true, "Zapisano", model);
        return displayAddForm(model);
    }

    public ModelAndView displayEditForm(Integer id, Model model) throws MovieNotFound {
        Movie movie = movieService.findById(id);
        MovieEditDto editDto = movieMapper.toEditDto(movie);

        List<CinemaDto> cinemaList = cinemaService.findAll().stream().map(cinemaMapper::toDto).collect(Collectors.toList());
        List<StageManagerDto> stageManagerList = stageManagerService.findAll().stream().map(stageManagerMapper::toDto).collect(Collectors.toList());
        List<ActorDto> actorDtoList = movie.getActorList().stream().map(actorMapper::toDto).collect(Collectors.toList());
        List<MovieDateDto> movieDateDtoList = movie.getMovieDateList().stream().map(movieDateMapper::toDto).collect(Collectors.toList());

        model.addAttribute("cinemaList", cinemaList);
        model.addAttribute("stageManagerList", stageManagerList);
        model.addAttribute("movieTypeList", MovieType.values());
        model.addAttribute("editDto", editDto);
        model.addAttribute("actorList", actorDtoList);
        model.addAttribute("movieDateList", movieDateDtoList);
        return new ModelAndView("admin/movie/edit");
    }

    public ModelAndView edit(MovieEditDto editDto, Model model) throws MovieNotFound, CinemaNotFound, StageManagerNotFound {
        Movie movie = movieService.findById(editDto.getId());
        Cinema cinema = cinemaService.findById(editDto.getCinemaId());
        StageManager stageManager = stageManagerService.findById(editDto.getStageManagerId());

        Movie toSave = movieMapper.toEditModel(movie, editDto, cinema, stageManager);
        movieService.save(toSave);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }

    public ModelAndView remove(Integer id) throws MovieNotFound {
        Movie movie = movieService.findById(id);
        for (User i : movie.getUserList()) {
            i.getMovieList().remove(movie);
        }

        movieService.remove(movie);
        return new ModelAndView("redirect:list");
    }

    public ModelAndView displayAddActorForm(Integer movieId, Model model) throws MovieNotFound {
        Movie movie = movieService.findById(movieId);
        List<Actor> actorList = actorService.findAll();
        actorList.removeAll(movie.getActorList());
        List<ActorDto> actorDtoList = actorList.stream().map(actorMapper::toDto).collect(Collectors.toList());

        model.addAttribute("actorList", actorDtoList);
        model.addAttribute("movieId", movieId);
        return new ModelAndView("admin/movie/addActor");
    }

    public ModelAndView addActor(Integer movieId, Integer actorId) throws MovieNotFound, ActorNotFound {
        Movie movie = movieService.findById(movieId);
        Actor actor = actorService.findById(actorId);

        movie.getActorList().add(actor);
        movieService.save(movie);
        return new ModelAndView("redirect:addActor", "movieId", movieId);
    }

    public ModelAndView removeActor(Integer movieId, Integer actorId) throws MovieNotFound, ActorNotFound {
        Movie movie = movieService.findById(movieId);
        Actor actor = actorService.findById(actorId);

        movie.getActorList().remove(actor);
        movieService.save(movie);
        return new ModelAndView("redirect:edit", "id", movieId);
    }

    public ModelAndView addMovieDate(Integer movieId, String date, String startTime, String endTime) throws MovieNotFound {
        Movie movie = movieService.findById(movieId);
        MovieDate movieDate = new MovieDate(null, LocalDate.parse(date), LocalTime.parse(startTime), LocalTime.parse(endTime), movie);
        movieDateService.save(movieDate);
        return new ModelAndView("redirect:edit", "id", movieId);
    }

    public ModelAndView removeMovieDate(Integer movieId, Integer movieDateId) throws MovieNotFound, MovieDateNotFound {
        Movie movie = movieService.findById(movieId);
        MovieDate movieDate = movieDateService.findById(movieDateId);

        movie.getMovieDateList().remove(movieDate);
        movieDateService.remove(movieDate);
        movieService.save(movie);
        return new ModelAndView("redirect:edit", "id", movieId);
    }
}
