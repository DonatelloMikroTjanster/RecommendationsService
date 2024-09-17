package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.salts.recommendationsservice.Entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
