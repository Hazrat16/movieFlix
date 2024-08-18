package com.movieflix.movieFlix.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer  movieId;

    @NotBlank(message="Please provide a movie title")
    private String title;

    @NotBlank(message="Please provide a movie director name")
    private String director;

    @NotBlank(message="Please provide a movie studio")
    private String studio;


    private Set<String> movieCast;

    private Integer releaseYear;

    @NotBlank(message="Please provide a movie poster")
    private String poster;

    @NotBlank(message="Please provide a poster's url")
    private String posterUrl;

}
