package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.salts.recommendationsservice.Entities.TopMedia;

import java.util.List;

public interface TopMediaRepository extends JpaRepository<TopMedia, Long> {
    List<TopMedia> findByUserId(Long userId);
}
