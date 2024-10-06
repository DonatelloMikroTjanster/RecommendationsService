package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.salts.recommendationsservice.Entities.PlaybackHistory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PlaybackHistoryRepository extends JpaRepository<PlaybackHistory, Long> {

   /* @Query("SELECT ph.media.id FROM PlaybackHistory ph WHERE ph.user.id = :userId")
    List<Long> findPlayedMediaIdsByUserId(@Param("userId") Long userId); */

    List<PlaybackHistory> findByUserId(Long userId);

    List<PlaybackHistory> findByUserIdAndPlayedAtAfter(Long userId, LocalDateTime playedAt);

}
