package com.example.cinemaapplication.view.admin;

import com.example.cinemaapplication.dto.movie.MovieAddDto;
import com.example.cinemaapplication.dto.movie.MovieEditDto;
import com.example.cinemaapplication.exception.actor.ActorNotFound;
import com.example.cinemaapplication.exception.cinema.CinemaNotFound;
import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.exception.movieDate.MovieDateNotFound;
import com.example.cinemaapplication.exception.stageManager.StageManagerNotFound;
import com.example.cinemaapplication.sevice.admin.MovieSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminMovieController")
@RequiredArgsConstructor
@RequestMapping("admin/movie")
public class MovieController {

    private final MovieSFC movieSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return movieSFC.displayList(model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return movieSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(MovieAddDto addDto, Model model) throws StageManagerNotFound, CinemaNotFound {
        return movieSFC.add(addDto, model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer id, Model model) throws MovieNotFound {
        return movieSFC.displayEditForm(id, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(MovieEditDto editDto, Model model) throws MovieNotFound, StageManagerNotFound, CinemaNotFound {
        return movieSFC.edit(editDto, model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws MovieNotFound {
        return movieSFC.remove(id);
    }

    @GetMapping("addActor")
    public ModelAndView displayAddActorForm(Integer movieId, Model model) throws MovieNotFound {
        return movieSFC.displayAddActorForm(movieId, model);
    }

    @PostMapping("addActor")
    public ModelAndView addActor(Integer movieId, Integer actorId) throws MovieNotFound, ActorNotFound {
        return movieSFC.addActor(movieId, actorId);
    }

    @PostMapping("removeActor")
    public ModelAndView removeActor(Integer movieId, Integer actorId) throws MovieNotFound, ActorNotFound {
        return movieSFC.removeActor(movieId, actorId);
    }

    @PostMapping("addMovieDate")
    public ModelAndView addMovieDate(Integer movieId, String date, String startTime, String endTime) throws MovieNotFound {
        return movieSFC.addMovieDate(movieId, date, startTime, endTime);
    }

    @PostMapping("removeMovieDate")
    public ModelAndView removeMovieDate(Integer movieId, Integer movieDateId) throws MovieNotFound, MovieDateNotFound {
        return movieSFC.removeMovieDate(movieId, movieDateId);
    }
}
