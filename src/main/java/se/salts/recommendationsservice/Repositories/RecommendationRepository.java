package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.salts.recommendationsservice.Entities.Recommendation;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    /*@Query("SELECT r.media.id FROM Recommendation r WHERE r.user.id = :userId AND r.listened = true")
    List<Long> findListenedMediaIdsByUserId(@Param("userId") Long userId);

    @Query("SELECT m.genre.id FROM Recommendation r JOIN r.media m WHERE r.user.id = :userId GROUP BY m.genre.id ORDER BY COUNT(r) DESC")
    List<Long> findTopGenresByUserId(@Param("userId") Long userId);

    List<Long> findPlayedMediaIds(Long userId); */
}
