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
    public ResponseEntity<List<Media>> getRecommendations(@PathVariable ("userId") Long userId) {
        try {
            List<Media> recommendations = recommendationService.getTopRecommendations(userId);
            List<Media> distinctRecommendations = recommendations.stream().distinct().collect(Collectors.toList());
            return ResponseEntity.ok(distinctRecommendations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


   /* @GetMapping("/{userId}")
    public ResponseEntity<List<Media>> getRecommendations(@PathVariable("userId") Long userId) {
        try {
            System.out.println("Fetching recommendations for userId: " + userId);
            List<Media> recommendations = recommendationService.getTopRecommendations(userId);
            List<Media> distinctRecommendations = recommendations.stream().distinct().collect(Collectors.toList());
            System.out.println("Recommendations fetched successfully for userId: " + userId);
            return ResponseEntity.ok(distinctRecommendations);
        } catch (RuntimeException e) {
            System.err.println("Runtime error fetching recommendations for userId: " + userId);
            e.printStackTrace();
            if (e.getMessage().equals("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            System.err.println("Error fetching recommendations for userId: " + userId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/



}
