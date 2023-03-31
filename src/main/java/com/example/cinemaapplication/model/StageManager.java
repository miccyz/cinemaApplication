package com.example.cinemaapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StageManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "stageManager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> moveList;

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
