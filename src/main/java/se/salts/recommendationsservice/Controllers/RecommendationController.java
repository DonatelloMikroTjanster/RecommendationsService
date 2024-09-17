package se.salts.recommendationsservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Services.RecommendationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Media>> getRecommendations(@PathVariable Long userId) {
        try {
            List<Media> recommendations = recommendationService.getTopRecommendations(userId);
            List<Media> distinctRecommendations = recommendations.stream().distinct().collect(Collectors.toList());
            return ResponseEntity.ok(distinctRecommendations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
