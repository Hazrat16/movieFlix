package com.movieflix.movieFlix.exceptions;

import com.movieflix.movieFlix.dto.MovieDto;
import org.springframework.http.ResponseEntity;

public class EmptyFileException extends Throwable {
    public EmptyFileException(String message) {
        super(message);
    }
}
