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

import java.time.LocalDate;
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
        genreMedia.add(new Media(1L, "Song 1", "Pop", "Music", LocalDate.now(), "url1", "3:30", user));
        genreMedia.add(new Media(2L, "Song 2", "Pop", "Music", LocalDate.now(), "url2", "4:00", user));
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
        genreMedia.add(new Media(1L, "Song 1", "Pop", "Music", LocalDate.now(), "url1", "3:30", user));
        genreMedia.add(new Media(2L, "Song 2", "Pop", "Music", LocalDate.now(), "url2", "4:00", user));
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

        Media mediaWithGenre = new Media(1L, "Song 1", "Pop", "Music", LocalDate.now(), "url1", "3:30", user);
        when(mediaRepository.findByGenreIdIn(new HashSet<>(topGenreIds))).thenReturn(Arrays.asList(mediaWithGenre));

        List<Long> listenedMediaIds = Arrays.asList(1L);
        when(recommendationRepository.findListenedMediaIdsByUserId(userId)).thenReturn(listenedMediaIds);

        List<Media> allMedia = new ArrayList<>();
        allMedia.add(new Media(2L, "Song 2", "Jazz", "Music", LocalDate.now(), "url2", "4:00", user));
        allMedia.add(new Media(3L, "Song 3", "Classical", "Music", LocalDate.now(), "url3", "5:00", user));
        when(mediaRepository.findAll()).thenReturn(allMedia);


        List<Media> recommendations = recommendationService.getTopRecommendations(userId);


        assertEquals(2, recommendations.size());
        assertTrue(recommendations.stream().anyMatch(media -> media.getTitle().equals("Song 2")));
        assertTrue(recommendations.stream().anyMatch(media -> media.getTitle().equals("Song 3")));
    }
}
