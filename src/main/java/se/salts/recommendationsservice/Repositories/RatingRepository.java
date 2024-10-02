package se.salts.recommendationsservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.salts.recommendationsservice.Entities.Rating;

import java.util.Collection;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByUserIdAndRatingLessThan(Long userId, String rating);

    List<Rating> findByUserIdAndRatingLessThanEqual(Long userId, String rating);

}
