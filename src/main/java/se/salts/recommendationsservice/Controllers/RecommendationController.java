package se.salts.recommendationsservice.Controllers;// RecommendationController.java
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Media> getRecommendations(@PathVariable Long userId) {
        List<Media> recommendations = recommendationService.getTopRecommendations(userId);
        return recommendations.stream().distinct().collect(Collectors.toList());
    }

}
