package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.salts.recommendationsservice.Entities.Media;

import java.util.List;
import java.util.Set;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByGenreIdIn(Set<Long> genreIds);
}
