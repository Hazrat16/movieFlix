package com.movieflix.movieFlix.service;

import com.movieflix.movieFlix.dto.MovieDto;
import com.movieflix.movieFlix.dto.MoviePageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;

    MovieDto getMovie(Integer movieId);

    List<MovieDto> getAllMovies();

    MovieDto updateMovie(MovieDto movieDto, MultipartFile file) throws IOException;

    void deleteMovie(Integer movieId);

    MoviePageResponse getMovieWithPagination(Integer pageNumber, Integer pageSize);

    MoviePageResponse getMovieWithPaginationAndSort(Integer pageNumber, Integer pageSize,String sortBy, String sortDirection);
}
