package se.salts.recommendationsservice.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.salts.recommendationsservice.Entities.Genre;
import se.salts.recommendationsservice.Entities.Media;
import se.salts.recommendationsservice.Entities.User;
import se.salts.recommendationsservice.Repositories.MediaRepository;
import se.salts.recommendationsservice.Repositories.RecommendationRepository;
import se.salts.recommendationsservice.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RecommendationServiceTest {

    @InjectMocks
    private RecommendationService recommendationService;

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTopRecommendationsWithListenedMedia() {

        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        List<Long> topGenreIds = Arrays.asList(1L, 2L, 3L);
        when(recommendationRepository.findTopGenresByUserId(userId)).thenReturn(topGenreIds);

        List<Media> genreMedia = new ArrayList<>();
        genreMedia.add(new Media(1L, "Song 1", null, null));
        genreMedia.add(new Media(2L, "Song 2", null, null));
        when(mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))).thenReturn(genreMedia);

        List<Long> listenedMediaIds = Arrays.asList(1L);
        when(recommendationRepository.findListenedMediaIdsByUserId(userId)).thenReturn(listenedMediaIds);


        List<Media> recommendations = recommendationService.getTopRecommendations(userId);

        // Then
        assertEquals(1, recommendations.size());
        assertEquals("Song 2", recommendations.get(0).getTitle());
    }

    @Test
    void testGetTopRecommendationsWithoutListenedMedia() {
        Long userId = 2L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        List<Long> topGenreIds = Arrays.asList(1L, 2L, 3L);
        when(recommendationRepository.findTopGenresByUserId(userId)).thenReturn(topGenreIds);

        List<Media> genreMedia = new ArrayList<>();
        genreMedia.add(new Media(1L, "Song 1", null, null));
        genreMedia.add(new Media(2L, "Song 2", null, null));
        when(mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))).thenReturn(genreMedia);

        List<Long> listenedMediaIds = new ArrayList<>();
        when(recommendationRepository.findListenedMediaIdsByUserId(userId)).thenReturn(listenedMediaIds);


        List<Media> recommendations = recommendationService.getTopRecommendations(userId);


        assertEquals(2, recommendations.size());
    }

    @Test
    void testGetTopRecommendationsWithInsufficientRecommendations() {
        Long userId = 3L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

        List<Long> topGenreIds = Arrays.asList(1L);
        when(recommendationRepository.findTopGenresByUserId(userId)).thenReturn(topGenreIds);

        Media mediaWithGenre = new Media(1L, "Song 1", new Genre(1L, "Pop"), null);
        when(mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))).thenReturn(Arrays.asList(mediaWithGenre));

        List<Long> listenedMediaIds = Arrays.asList(1L);
        when(recommendationRepository.findListenedMediaIdsByUserId(userId)).thenReturn(listenedMediaIds);

        List<Media> allMedia = new ArrayList<>();
        allMedia.add(new Media(2L, "Song 2", new Genre(2L, "Jazz"), null)); // Ensure genre is set
        allMedia.add(new Media(3L, "Song 3", new Genre(3L, "Classical"), null)); // Ensure genre is set
        when(mediaRepository.findAll()).thenReturn(allMedia);


        List<Media> recommendations = recommendationService.getTopRecommendations(userId);


        assertEquals(2, recommendations.size());
        assertTrue(recommendations.stream().anyMatch(media -> media.getTitle().equals("Song 2")));
        assertTrue(recommendations.stream().anyMatch(media -> media.getTitle().equals("Song 3")));
    }
}
