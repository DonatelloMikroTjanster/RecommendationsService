package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.salts.recommendationsservice.Entities.Recommendation;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByUserId(Long userId);


}
