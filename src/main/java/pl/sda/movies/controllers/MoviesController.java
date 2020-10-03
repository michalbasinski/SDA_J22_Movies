package pl.sda.movies.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.movies.entities.Movie;
import pl.sda.movies.services.MovieService;

import java.util.List;

@RestController
public class MoviesController {

    private MovieService service;

    @Autowired
    public MoviesController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return service.getAllMovies();
    }

    @PostMapping("/movies")
    public Movie saveMovie(@RequestBody Movie movieToSave) {
        return service.saveMovie(movieToSave);
    }

    @DeleteMapping("/movies/{id}")
    public Boolean deleteMovie(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PutMapping("/movies")
    public ResponseEntity<String> updateMovie(@RequestBody Movie updatedMovie) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Movie movie = service.updateMovie(updatedMovie);
            String movieJSON = mapper.writeValueAsString(movie);
            return new ResponseEntity<>(movieJSON, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
