package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.salts.recommendationsservice.Entities.Media;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m WHERE m.genre.id IN :genreIds AND m.id NOT IN :listenedMediaIds")
    List<Media> findMediaByGenreAndNotIn(@Param("genreIds") List<Long> genreIds, @Param("listenedMediaIds") List<Long> listenedMediaIds);

    @Query("SELECT m FROM Media m WHERE m.genre.id NOT IN :excludedGenreIds AND m.id NOT IN :listenedMediaIds")
    List<Media> findMediaByNotInGenresAndNotIn(@Param("excludedGenreIds") List<Long> excludedGenreIds, @Param("listenedMediaIds") List<Long> listenedMediaIds);
}
