package com.movieflix.movieFlix.service;


import com.movieflix.movieFlix.dto.MovieDto;
import com.movieflix.movieFlix.entity.Movie;
import com.movieflix.movieFlix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final FileService fileService;

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {

        //upload file
        String uploadedFileName = fileService.uploadFile(path,file);

        //set the value of field 'poster'
        movieDto.setPoster(uploadedFileName);

        //map dto to movie obj
        Movie movie = new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPosterUrl()
        );

        //save movie object -> saved Movie object
       Movie savedMovie = movieRepository.save(movie);

        //generate poster url
        String posterUrl = baseUrl + "/file/" + uploadedFileName;

        //map movie obj to dto
        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {

        //check it is existing or not.if exists fetch it
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));

        //generate poster url
        String posterUrl = baseUrl + "/file/" + movie.getPoster();

        //map to movieDto
        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {

        // Fetch all movies
        List<Movie> movies = movieRepository.findAll();

        // Convert to MovieDto using Streams
        return movies.stream()
                .map(movie -> new MovieDto(
                        movie.getMovieId(),
                        movie.getTitle(),
                        movie.getDirector(),
                        movie.getStudio(),
                        movie.getMovieCast(),
                        movie.getReleaseYear(),
                        movie.getPoster(),
                        baseUrl + "/file/" + movie.getPoster()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, MultipartFile file) throws IOException {

        //implement this
        String uploadedFileName = fileService.uploadFile(path,file);
        movieDto.setPoster(uploadedFileName);
        Movie movie = new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPosterUrl()
        );
        Movie updatedMovie = movieRepository.save(movie);
        String posterUrl = baseUrl + "/file/" + uploadedFileName;
        MovieDto updatedResponse = new MovieDto(
                updatedMovie.getMovieId(),
                updatedMovie.getTitle(),
                updatedMovie.getDirector(),
                updatedMovie.getStudio(),
                updatedMovie.getMovieCast(),
                updatedMovie.getReleaseYear(),
                updatedMovie.getPoster(),
                posterUrl
        );

        return updatedResponse;
    }

    @Override
    public void deleteMovie(Integer movieId) {
        movieRepository.deleteById(movieId);

    }
}
