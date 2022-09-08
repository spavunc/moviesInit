package com.init.task.movie;

import java.util.Optional;

public interface MovieService {
    Optional<Movie> findById(Long id);
    Optional<Movie> findByName(String name);
    Movie saveMovie(MovieCommand movie);
    void deleteById(Long id);
    Optional<Movie> editMovieDetails(Long id, MovieCommand movie);

}
