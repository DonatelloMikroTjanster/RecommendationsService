package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.Recommendation;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Repositories.MediaRepository;
import se.salts.recommendationsservice.Repositories.RecommendationRepository;
import se.salts.recommendationsservice.Repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Media> getTopRecommendations(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Long> topGenreIds = getTopGenreIds(userId);

        List<Media> genreMedia = mediaRepository.findByGenreIdIn(topGenreIds);

        List<Media> otherGenreMedia = mediaRepository.findAll();
        otherGenreMedia.removeAll(genreMedia);

        Collections.shuffle(otherGenreMedia);
        int numberOfOtherGenreRecommendations = (int) Math.ceil(otherGenreMedia.size() * 0.20);
        List<Media> selectedOtherGenreMedia = otherGenreMedia.subList(0, Math.min(numberOfOtherGenreRecommendations, otherGenreMedia.size()));

        List<Media> allRecommendations = new ArrayList<>(genreMedia);
        allRecommendations.addAll(selectedOtherGenreMedia);
        allRecommendations = allRecommendations.stream().distinct().collect(Collectors.toList());

        return allRecommendations.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    private Set<Long> getTopGenreIds(Long userId) {
        return new HashSet<>();
    }
}
