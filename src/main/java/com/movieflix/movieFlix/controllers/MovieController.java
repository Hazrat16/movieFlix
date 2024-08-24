package com.movieflix.movieFlix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.movieFlix.dto.MovieDto;
import com.movieflix.movieFlix.exceptions.EmptyFileException;
import com.movieflix.movieFlix.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestParam String movieDto) throws IOException, EmptyFileException {

        if(file.isEmpty()) {
            throw new EmptyFileException("File is empty! Please send a file");
        }

    MovieDto dto = convertMovieToDto(movieDto);
    return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);


    }

    private MovieDto convertMovieToDto(String movieDtoObj) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(movieDtoObj, MovieDto.class);

    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMoviesHandler()  {
        List<MovieDto> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieByIdHandler(@PathVariable Integer movieId) {

        return ResponseEntity.ok(movieService.getMovie(movieId));
    }


    @PutMapping("/update-movie")
    public ResponseEntity<MovieDto> updateMovieHandler(@RequestPart("file") MultipartFile file,
                                                       @RequestParam("movieDto") String movieDtoString) throws IOException {

        // Convert movieDtoString to MovieDto object
        MovieDto movieDto;
        try {
            movieDto = convertMovieToDto(movieDtoString);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // Process the update
        MovieDto updatedMovie = movieService.updateMovie(movieDto, file);

        // Return the updated movie DTO
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovieHandler( @PathVariable String movieId) {
        movieService.deleteMovie(Integer.valueOf(movieId));

    }

}
