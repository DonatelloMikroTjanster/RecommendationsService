package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.salts.recommendationsservice.Entities.Media;

import java.util.List;
import java.util.Set;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m WHERE m.genre.id IN :genreIds AND m.id NOT IN :listenedMediaIds")
    List<Media> findByGenreIdInAndNotListened(@Param("genreIds") Set<Long> genreIds,
                                              @Param("listenedMediaIds") List<Long> listenedMediaIds);

    List<Media> findByGenreIdIn(Set<Long> genreIds);
}
