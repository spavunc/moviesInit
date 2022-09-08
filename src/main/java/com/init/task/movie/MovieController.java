package com.init.task.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieServiceImpl movieService;

    MovieController(MovieServiceImpl movieService){
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Optional<Movie> movie = movieService.findById(id);
        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name){
        Optional<Movie> movie = movieService.findByName(name);
        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Movie> save(@RequestBody @Valid final MovieCommand movieCommand) {
        Movie created = movieService.saveMovie(movieCommand);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovieById(@PathVariable Long id){
        movieService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Movie> editMovieById(@PathVariable Long id, @Valid @RequestBody final MovieCommand movie){
        Optional<Movie> created = movieService.editMovieDetails(id, movie);
        if(created.isPresent()){
            return new ResponseEntity<>(created.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{pageNo}/{pageSize}")
    public List<Movie> getPaginatedCountries(@PathVariable int pageNo,
                                             @PathVariable int pageSize) {
        return movieService.findPaginated(pageNo, pageSize);
    }
}
