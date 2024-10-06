/*package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Entities.PlaybackHistory;
import se.salts.recommendationsservice.Entities.Rating;
import se.salts.recommendationsservice.Repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    // Injecting repositories to interact with the database
    private final RecommendationRepository recommendationRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final PlaybackHistoryRepository playbackHistoryRepository;
    private final RatingRepository ratingRepository;
    private final TopMediaRepository topMediaRepository;

    @Autowired
    public RecommendationService(
            RecommendationRepository recommendationRepository,
            MediaRepository mediaRepository,
            UserRepository userRepository,
            PlaybackHistoryRepository playbackHistoryRepository,
            RatingRepository ratingRepository,
            TopMediaRepository topMediaRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.playbackHistoryRepository = playbackHistoryRepository;
        this.ratingRepository = ratingRepository;
        this.topMediaRepository = topMediaRepository;
    }

    // Method to get the top 10 media recommendations for a user
    public List<Media> getTopRecommendations(Long userId) {
        // Fetch the user from the database; throw an error if the user doesn't exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the list of media that the user has already played
        // Ensure explicit typing for PlaybackHistory in the stream
        List<Long> playedMediaIds = playbackHistoryRepository.findByUserId(userId).stream()
                .map(playbackHistory -> ((PlaybackHistory) playbackHistory).getMedia().getId())  // Explicit cast
                .collect(Collectors.toList());

        // Fetch media that the user has rated positively (e.g., thumbs up)
        List<Long> positivelyRatedMediaIds = ratingRepository.findByUserIdAndRating(userId, String.valueOf(4))
                .stream()
                .map(rating -> ((Rating) rating).getMedia().getId())  // Explicit cast for Rating
                .collect(Collectors.toList());

        // Exclude media that the user has played or negatively rated (e.g., thumbs down)
        List<Long> excludedMediaIds = ratingRepository.findByUserIdAndRatingLessThan(userId, String.valueOf(3))
                .stream()
                .map(rating -> ((Rating) rating).getMedia().getId())  // Explicit cast for Rating
                .collect(Collectors.toList());

        // Combine played media and negatively rated media into one list to be excluded
        excludedMediaIds.addAll(playedMediaIds);

        // Fetch media based on the user's top genres for personalized recommendations
        List<Long> topGenreIds = getTopGenresForUser(userId); // User's top 3 genres
        List<Media> genreMedia = mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played/negatively rated media
                .collect(Collectors.toList());

        // Fetch media from other genres for the 20% genre diversity (i.e., 2 out of 10 recommendations)
        List<Media> otherGenreMedia = mediaRepository.findByGenreIdNotIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played/negatively rated media
                .limit(2)  // Limit to 20% of the recommendations (2 out of 10)
                .collect(Collectors.toList());

        // Combine personalized (80%) and diverse (20%) recommendations into one list
        List<Media> allRecommendations = combineRecommendations(genreMedia, otherGenreMedia);

        // If more than 10 recommendations are available, limit the total recommendations to exactly 10
        if (allRecommendations.size() > 10) {
            allRecommendations = allRecommendations.subList(0, 10);
        }

        return allRecommendations;
    }

    // Method to fetch the top 3 genres for a user based on their media consumption or ratings
    private List<Long> getTopGenresForUser(Long userId) {
        // This method can query the recommendation repository to return the top genres by user ID
        return recommendationRepository.findTopGenresByUserId(userId);
    }

    // Method to fill the recommendation list with remaining media if not enough recommendations are found
    private List<Media> fillRemainingRecommendations(List<Media> currentRecommendations, List<Long> excludedMediaIds) {
        List<Media> allMedia = mediaRepository.findAll();

        // Filter out media that has been excluded (already played or negatively rated)
        List<Media> remainingMedia = allMedia.stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude media already interacted with
                .filter(media -> !currentRecommendations.contains(media))  // Exclude media already in the recommendation list
                .collect(Collectors.toList());

        // Shuffle remaining media for randomness and fill the list up to 10 recommendations
        Collections.shuffle(remainingMedia);
        currentRecommendations.addAll(remainingMedia.stream()
                .limit(10 - currentRecommendations.size())  // Ensure exactly 10 recommendations
                .collect(Collectors.toList()));

        return currentRecommendations;
    }

    // Method to combine the top genre-specific media (80%) and other genre media (20%) into a single list
    private List<Media> combineRecommendations(List<Media> genreMedia, List<Media> otherGenreMedia) {
        List<Media> allRecommendations = new ArrayList<>();

        // Add up to 8 media items from the user's top genres (80% of 10 recommendations)
        allRecommendations.addAll(genreMedia.stream().limit(8).collect(Collectors.toList()));

        // Add 2 media items from other genres (20% of 10 recommendations)
        allRecommendations.addAll(otherGenreMedia);

        return allRecommendations;
    }
} */
/*package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.TopMedia;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final TopMediaRepository topMediaRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public RecommendationService(
            RecommendationRepository recommendationRepository,
            MediaRepository mediaRepository,
            UserRepository userRepository,
            TopMediaRepository topMediaRepository,
            RatingRepository ratingRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.topMediaRepository = topMediaRepository;
        this.ratingRepository = ratingRepository;
    }

    // Method to get the top 10 media recommendations for a user
    public List<Media> getTopRecommendations(Long userId) {
        // Fetch the user from the database; throw an error if the user doesn't exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the list of media that the user has already played
        List<Long> playedMediaIds = recommendationRepository.findPlayedMediaIds(userId);

        // Fetch media that the user has rated negatively (thumbs down)
        List<Long> negativelyRatedMediaIds = ratingRepository.findByUserIdAndRatingLessThan(userId, "UP")
                .stream()
                .map(rating -> rating.getMedia().getId())
                .collect(Collectors.toList());

        // Combine played media and negatively rated media into one list to exclude
        List<Long> excludedMediaIds = new ArrayList<>();
        excludedMediaIds.addAll(playedMediaIds);
        excludedMediaIds.addAll(negativelyRatedMediaIds);

        // Fetch the user's top 3 genres based on TopMedia
        List<Long> topGenreIds = getTopGenresForUser(userId);

        // Fetch media from the user's top genres
        List<Media> genreMedia = mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played/negatively rated media
                .collect(Collectors.toList());

        // Fetch media from other genres for the diversity requirement
        List<Media> otherGenreMedia = mediaRepository.findByGenreIdNotIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played/negatively rated media
                .limit(2)  // Limit to 20% of recommendations (2 out of 10)
                .collect(Collectors.toList());

        // Combine personalized recommendations (80%) and diverse recommendations (20%)
        List<Media> allRecommendations = combineRecommendations(genreMedia, otherGenreMedia);

        // If more than 10 recommendations are available, limit the total recommendations to exactly 10
        if (allRecommendations.size() > 10) {
            allRecommendations = allRecommendations.subList(0, 10);
        }

        return allRecommendations;
    }

    // Method to fetch the top 3 genres for a user based on their top media (most played media)
    private List<Long> getTopGenresForUser(Long userId) {
        // Fetch the top media for the user
        List<TopMedia> topMediaList = topMediaRepository.findByUserId(userId);

        // Create a map to count how many times each genre has been played
        Map<Long, Long> genrePlayCounts = new HashMap<>();

        // Iterate over top media and count plays for each genre
        for (TopMedia topMedia : topMediaList) {
            Long genreId = topMedia.getMedia().getGenre().getId();
            Long playCount = topMedia.getPlayCount();

            // Accumulate the play counts for each genre
            genrePlayCounts.put(genreId, genrePlayCounts.getOrDefault(genreId, 0L) + playCount);
        }

        // Sort the genres by total play count and return the top 3
        return genrePlayCounts.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Method to combine the top genre-specific media (80%) and other genre media (20%) into a single list
    private List<Media> combineRecommendations(List<Media> genreMedia, List<Media> otherGenreMedia) {
        List<Media> allRecommendations = new ArrayList<>();

        // Add up to 8 media items from the user's top genres (80% of 10 recommendations)
        allRecommendations.addAll(genreMedia.stream().limit(8).collect(Collectors.toList()));

        // Add 2 media items from other genres (20% of 10 recommendations)
        allRecommendations.addAll(otherGenreMedia);

        return allRecommendations;
    }
} */

/*
package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.*;
import se.salts.recommendationsservice.Repositories.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    // Injecting repositories to interact with the database
    private final RecommendationRepository recommendationRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final PlaybackHistoryRepository playbackHistoryRepository;
    private final RatingRepository ratingRepository;
    private final TopMediaRepository topMediaRepository;

    @Autowired
    public RecommendationService(
            RecommendationRepository recommendationRepository,
            MediaRepository mediaRepository,
            UserRepository userRepository,
            PlaybackHistoryRepository playbackHistoryRepository,
            RatingRepository ratingRepository,
            TopMediaRepository topMediaRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.playbackHistoryRepository = playbackHistoryRepository;
        this.ratingRepository = ratingRepository;
        this.topMediaRepository = topMediaRepository;
    }

    // Main method to get the top 10 media recommendations for a user
    public List<Media> getTopRecommendations(Long userId) {
        // Fetch the user from the database; throw an error if the user doesn't exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch media that the user has already played using the playback history repository
        List<Long> playedMediaIds = playbackHistoryRepository.findByUserId(userId).stream()
                .map(PlaybackHistory::getMedia)  // Get Media from PlaybackHistory
                .map(Media::getId)  // Get Media ID
                .collect(Collectors.toList());

        // Fetch media that the user has rated negatively using the rating repository
        List<Long> negativelyRatedMediaIds = ratingRepository.findByUserIdAndRatingLessThan(userId, "UP")
                .stream()
                .map(Rating::getMedia)  // Get Media from Rating
                .map(Media::getId)  // Get Media ID
                .collect(Collectors.toList());

        // Combine played media and negatively rated media into one list to exclude them from recommendations
        List<Long> excludedMediaIds = new ArrayList<>();
        excludedMediaIds.addAll(playedMediaIds);
        excludedMediaIds.addAll(negativelyRatedMediaIds);

        // Fetch the user's top 3 genres based on their most-played media using the top media repository
        List<Long> topGenreIds = getTopGenresForUser(userId);

        // Fetch media from the user's top genres using the media repository
        List<Media> genreMedia = mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played or negatively rated media
                .collect(Collectors.toList());

        // Fetch media from genres other than the top genres for diversity
        List<Media> otherGenreMedia = mediaRepository.findByGenreIdNotIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played or negatively rated media
                .limit(2)  // Limit to 20% of recommendations (2 out of 10)
                .collect(Collectors.toList());

        // Combine personalized recommendations (from top genres) and diverse recommendations (from other genres)
        List<Media> allRecommendations = combineRecommendations(genreMedia, otherGenreMedia);

        // If more than 10 recommendations are found, limit the total to exactly 10
        if (allRecommendations.size() > 10) {
            allRecommendations = allRecommendations.subList(0, 10);
        }

        return allRecommendations;
    }

    // Method to get the top 3 genres for a user based on their most-played media (from TopMedia)
    private List<Long> getTopGenresForUser(Long userId) {
        // Fetch the top media entries for the user
        List<TopMedia> topMediaList = topMediaRepository.findByUserId(userId);

        // Create a map to count how many times each genre has been played
        Map<Long, Long> genrePlayCounts = new HashMap<>();

        // Iterate over top media and count the total play counts for each genre
        for (TopMedia topMedia : topMediaList) {
            Long genreId = topMedia.getMedia().getGenre().getId();  // Get the Genre ID
            Long playCount = topMedia.getPlayCount();  // Get the play count

            // Accumulate the play counts for each genre
            genrePlayCounts.put(genreId, genrePlayCounts.getOrDefault(genreId, 0L) + playCount);
        }

        // Sort the genres by total play count and return the top 3 genres
        return genrePlayCounts.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())  // Sort by play count in descending order
                .limit(3)  // Limit to the top 3 genres
                .map(Map.Entry::getKey)  // Get the Genre ID
                .collect(Collectors.toList());
    }

    // Method to combine personalized and diverse recommendations into a single list
    private List<Media> combineRecommendations(List<Media> genreMedia, List<Media> otherGenreMedia) {
        List<Media> allRecommendations = new ArrayList<>();

        // Add up to 8 media items from the user's top genres (80% of 10 recommendations)
        allRecommendations.addAll(genreMedia.stream().limit(8).collect(Collectors.toList()));

        // Add 2 media items from other genres (20% of 10 recommendations)
        allRecommendations.addAll(otherGenreMedia);

        return allRecommendations;
    }
} */
package se.salts.recommendationsservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salts.recommendationsservice.Entities.*;
import se.salts.recommendationsservice.Repositories.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final MediaRepository mediaRepository;
    private final UserRepository userRepository;
    private final PlaybackHistoryRepository playbackHistoryRepository;
    private final RatingRepository ratingRepository;
    private final TopMediaRepository topMediaRepository;

    @Autowired
    public RecommendationService(
            RecommendationRepository recommendationRepository,
            MediaRepository mediaRepository,
            UserRepository userRepository,
            PlaybackHistoryRepository playbackHistoryRepository,
            RatingRepository ratingRepository,
            TopMediaRepository topMediaRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.mediaRepository = mediaRepository;
        this.userRepository = userRepository;
        this.playbackHistoryRepository = playbackHistoryRepository;
        this.ratingRepository = ratingRepository;
        this.topMediaRepository = topMediaRepository;
    }

    // Main method to get the top 10 media recommendations for a user
    public List<Media> getTopRecommendations(Long userId) {
        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch media already played in the last 30 days
        List<Long> recentlyPlayedMediaIds = playbackHistoryRepository.findByUserIdAndPlayedAtAfter(
                        userId, LocalDateTime.now().minusDays(30))
                .stream()
                .map(PlaybackHistory::getMedia)
                .map(Media::getId)
                .collect(Collectors.toList());

        // Fetch negatively rated media (rated 1 or 2)
        List<Long> veryNegativelyRatedMediaIds = ratingRepository.findByUserIdAndRatingLessThanEqual(userId, "DOWN")
                .stream()
                .map(Rating::getMedia)
                .map(Media::getId)
                .collect(Collectors.toList());

        // Combine played and negatively rated media into one exclusion list
        List<Long> excludedMediaIds = new ArrayList<>();
        excludedMediaIds.addAll(recentlyPlayedMediaIds);
        excludedMediaIds.addAll(veryNegativelyRatedMediaIds);

        // Fetch the user's top genres based on TopMedia
        List<Long> topGenreIds = getTopGenresForUser(userId);

        // Fetch media from the user's top genres
        List<Media> genreMedia = mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played or heavily disliked media
                .collect(Collectors.toList());

        // Fetch media from other genres for diversity
        List<Media> otherGenreMedia = mediaRepository.findByGenreIdNotIn(new HashSet<>(topGenreIds))
                .stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))  // Exclude played or heavily disliked media
                .limit(2)  // Limit to 2 media from other genres
                .collect(Collectors.toList());

        // Combine personalized recommendations and diverse recommendations
        List<Media> allRecommendations = combineRecommendations(genreMedia, otherGenreMedia);

        // If fewer than 10, fill the remaining spots with fallback media
        if (allRecommendations.size() < 10) {
            allRecommendations = fillRemainingRecommendations(allRecommendations, excludedMediaIds);
        }

        // Limit to 10 recommendations
        return allRecommendations.subList(0, Math.min(10, allRecommendations.size()));
    }

    // Helper method to get the user's top genres based on play count
    private List<Long> getTopGenresForUser(Long userId) {
        List<TopMedia> topMediaList = topMediaRepository.findByUserId(userId);
        Map<Long, Long> genrePlayCounts = new HashMap<>();

        for (TopMedia topMedia : topMediaList) {
            Long genreId = topMedia.getMedia().getGenre().getId();
            Long playCount = topMedia.getPlayCount();
            genrePlayCounts.put(genreId, genrePlayCounts.getOrDefault(genreId, 0L) + playCount);
        }

        return genrePlayCounts.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Helper method to combine personalized and diverse recommendations
    private List<Media> combineRecommendations(List<Media> genreMedia, List<Media> otherGenreMedia) {
        List<Media> allRecommendations = new ArrayList<>();
        allRecommendations.addAll(genreMedia.stream().limit(8).collect(Collectors.toList()));
        allRecommendations.addAll(otherGenreMedia);
        return allRecommendations;
    }

    // Helper method to fill remaining recommendations
    private List<Media> fillRemainingRecommendations(List<Media> currentRecommendations, List<Long> excludedMediaIds) {
        List<Media> allMedia = mediaRepository.findAll();
        List<Media> remainingMedia = allMedia.stream()
                .filter(media -> !excludedMediaIds.contains(media.getId()))
                .filter(media -> !currentRecommendations.contains(media))
                .collect(Collectors.toList());

        Collections.shuffle(remainingMedia);
        currentRecommendations.addAll(remainingMedia.stream().limit(10 - currentRecommendations.size()).collect(Collectors.toList()));

        return currentRecommendations;
    }
}


