package com.example.cinemaapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private LocalDate releaseDate;
    private MovieType type;
    private int durationInMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovieDate> movieDateList;

    @ManyToOne(fetch = FetchType.LAZY)
    private StageManager stageManager;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movieActor",
            joinColumns = {@JoinColumn(name = "movieId")},
            inverseJoinColumns = {@JoinColumn(name = "actorId")}
    )
    private List<Actor> actorList;

    @ManyToMany(mappedBy = "movieList", fetch = FetchType.LAZY)
    private List<User> userList;
}
