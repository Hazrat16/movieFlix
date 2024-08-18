package com.movieflix.movieFlix.repository;

import com.movieflix.movieFlix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
