package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Repositories.MediaRepository;
import se.salts.recommendationsservice.Repositories.RecommendationRepository;
import se.salts.recommendationsservice.Repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, MediaRepository mediaRepository, UserRepository userRepository) {
        this.recommendationRepository = recommendationRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
    }

    public List<Media> getTopRecommendations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Long> topGenreIds = getTopGenresForUser(userId);

        List<Long> listenedMediaIds = recommendationRepository.findListenedMediaIdsByUserId(userId);
        List<Media> genreMedia = mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !listenedMediaIds.contains(media.getId()))
                .collect(Collectors.toList());

        List<Media> otherGenreMedia = getOtherGenreMedia(topGenreIds, listenedMediaIds);

        List<Media> allRecommendations = combineRecommendations(genreMedia, otherGenreMedia);

        if (allRecommendations.size() < 10) {
            allRecommendations = fillRemainingRecommendations(allRecommendations, listenedMediaIds);
        }

        return allRecommendations.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    private List<Long> getTopGenresForUser(Long userId) {
        return recommendationRepository.findTopGenresByUserId(userId).stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    private List<Media> getOtherGenreMedia(List<Long> topGenreIds, List<Long> listenedMediaIds) {
        List<Media> allMedia = mediaRepository.findAll();
        List<Media> otherGenreMedia = allMedia.stream()
                .filter(media -> !topGenreIds.contains(media.getGenre()))
                .filter(m -> !listenedMediaIds.contains(m.getId()))
                .collect(Collectors.toList());

        Collections.shuffle(otherGenreMedia);
        int numberOfOtherGenreRecommendations = (int) Math.ceil(10 * 0.20);
        return otherGenreMedia.stream()
                .limit(numberOfOtherGenreRecommendations)
                .collect(Collectors.toList());
    }

    private List<Media> combineRecommendations(List<Media> genreMedia, List<Media> otherGenreMedia) {
        List<Media> allRecommendations = new ArrayList<>(genreMedia);
        allRecommendations.addAll(otherGenreMedia);
        return allRecommendations.stream().distinct().collect(Collectors.toList());
    }

    private List<Media> fillRemainingRecommendations(List<Media> currentRecommendations, List<Long> listenedMediaIds) {
        List<Media> allMedia = mediaRepository.findAll();
        List<Media> remainingMedia = allMedia.stream()
                .filter(media -> !listenedMediaIds.contains(media.getId()))
                .filter(media -> !currentRecommendations.contains(media))
                .collect(Collectors.toList());

        Collections.shuffle(remainingMedia);
        currentRecommendations.addAll(remainingMedia.stream()
                .limit(10 - currentRecommendations.size())
                .collect(Collectors.toList()));

        return currentRecommendations;
    }
}
