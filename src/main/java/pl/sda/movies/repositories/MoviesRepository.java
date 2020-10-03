package pl.sda.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.movies.entities.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
}
