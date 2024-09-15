package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Repositories.MediaRepository;
import se.salts.recommendationsservice.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Media> getTopRecommendations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Long> listenedMediaIds = user.getMedia().stream().map(Media::getId).collect(Collectors.toList());
        List<Long> topGenreIds = userRepository.findTopGenresForUser(userId).stream().limit(3).collect(Collectors.toList());

        List<Media> mediaFromTopGenres = mediaRepository.findMediaByGenreAndNotIn(topGenreIds, listenedMediaIds);
        List<Long> excludedGenreIds = new ArrayList<>(topGenreIds);
        List<Media> mediaFromOtherGenres = mediaRepository.findMediaByNotInGenresAndNotIn(excludedGenreIds, listenedMediaIds);

        int otherGenreCount = (int) Math.ceil(0.2 * 10); // 20% of 10 = 2
        Set<Media> recommendedMedia = mediaFromTopGenres.stream().collect(Collectors.toSet());

        if (mediaFromOtherGenres.size() < otherGenreCount) {
            recommendedMedia.addAll(mediaFromOtherGenres);
        } else {
            recommendedMedia.addAll(mediaFromOtherGenres.subList(0, otherGenreCount));
        }

        if (recommendedMedia.size() > 10) {
            recommendedMedia = recommendedMedia.stream().limit(10).collect(Collectors.toSet());
        }

        List<Media> similarMedia = getSimilarMedia(userId);
        if (recommendedMedia.size() < 10) {
            int remainingSpace = 10 - recommendedMedia.size();
            recommendedMedia.addAll(similarMedia.stream().limit(remainingSpace).collect(Collectors.toList()));
        }

        return recommendedMedia.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    private List<Media> getSimilarMedia(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Long> listenedMediaIds = user.getMedia().stream().map(Media::getId).collect(Collectors.toList());

        List<Long> genres = user.getMedia().stream()
                .map(media -> media.getGenre().getId())
                .distinct()
                .collect(Collectors.toList());

        List<Media> similarMedia = mediaRepository.findMediaByGenreAndNotIn(genres, listenedMediaIds);
        return similarMedia.stream().limit(10).collect(Collectors.toList());
    }
}
