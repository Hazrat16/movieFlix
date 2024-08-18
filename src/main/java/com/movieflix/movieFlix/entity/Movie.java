package com.movieflix.movieFlix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  movieId;

    @Column(nullable = false)
    @NotBlank(message="Please provide a movie title")
    private String title;

    @Column(nullable = false)
    @NotBlank(message="Please provide a movie director name")
    private String director;

    @Column(nullable = false)
    @NotBlank(message="Please provide a movie studio")
    private String studio;


    @ElementCollection
    @CollectionTable(name="movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message="Please provide a movie poster")
    private String poster;


}
