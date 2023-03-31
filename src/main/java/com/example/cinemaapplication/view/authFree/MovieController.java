package com.example.cinemaapplication.view.authFree;

import com.example.cinemaapplication.exception.movie.MovieNotFound;
import com.example.cinemaapplication.model.MovieType;
import com.example.cinemaapplication.sevice.authFree.MovieSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("authFreeMovieController")
@RequiredArgsConstructor
@RequestMapping("authFree/movie")
@Scope("session")
public class MovieController {

    private final MovieSFC movieSFC;

    private String title = "";
    private String releaseDate = "";
    private String movieDate = "";
    private Integer cinemaId = -1;
    private Integer stageManagerId = -1;
    private Integer actorId = -1;
    private MovieType movieType = MovieType.ALL;

    @GetMapping("list")
    public ModelAndView displayList(String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType, Model model) {
        filter(title, releaseDate, movieDate, cinemaId, stageManagerId, actorId, movieType);
        return movieSFC.displayList(this.title, this.releaseDate, this.movieDate, this.cinemaId, this.stageManagerId, this.actorId, this.movieType, model);
    }

    @GetMapping("details")
    public ModelAndView displayDetails(Integer id, Model model) throws MovieNotFound {
        return movieSFC.displayDetails(id, model);
    }

    private void filter(String title, String releaseDate, String movieDate, Integer cinemaId, Integer stageManagerId, Integer actorId, MovieType movieType) {
        if (title != null) this.title = title;
        if (releaseDate != null) this.releaseDate = releaseDate;
        if (movieDate != null) this.movieDate = movieDate;
        if (cinemaId != null) this.cinemaId = cinemaId;
        if (stageManagerId != null) this.stageManagerId = stageManagerId;
        if (actorId != null) this.actorId = actorId;
        if (movieType != null) this.movieType = movieType;
    }
}
