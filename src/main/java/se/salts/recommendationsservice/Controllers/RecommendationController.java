package se.salts.recommendationsservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salts.recommendationsservice.Entities.Recommendation;
import se.salts.recommendationsservice.Services.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsForUser(@PathVariable Long userId) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsForUser(userId);
        if (recommendations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        Recommendation createdRecommendation = recommendationService.createRecommendation(recommendation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecommendation);
    }
}
