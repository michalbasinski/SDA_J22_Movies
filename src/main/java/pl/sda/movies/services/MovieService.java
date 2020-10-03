package pl.sda.movies.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.movies.entities.Movie;
import pl.sda.movies.repositories.MoviesRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MovieService {

    private MoviesRepository repository;

    @Autowired
    public MovieService(MoviesRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return repository.save(movie);
    }

    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Movie updateMovie(Movie updatedMovie) {
        Movie movieFromDB = repository.findById(updatedMovie.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono rekordu o id " + updatedMovie.getId()));

        movieFromDB.setTitle(updatedMovie.getTitle());
        movieFromDB.setYear(updatedMovie.getYear());
        movieFromDB.setDirector(updatedMovie.getDirector());

        return repository.save(movieFromDB);

    }
}
