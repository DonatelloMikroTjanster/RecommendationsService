package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Recommendation;
import se.salts.recommendationsservice.Repositories.RecommendationRepository;

import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<Recommendation> getRecommendationsForUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation createRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}
