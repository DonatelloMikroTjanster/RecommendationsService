-- Insert Users
INSERT IGNORE INTO user (id, username, email, created_at)
VALUES
    (1, 'kalle_anka', 'kalle@example.com', '2023-01-01T10:00:00'),
    (2, 'lisa_svensson', 'lisa@example.com', '2023-01-02T11:00:00'),
    (3, 'erik_eriksson', 'erik@example.com', '2023-01-03T12:00:00'),
    (4, 'anna_andersson', 'anna@example.com', '2023-01-04T13:00:00');

-- Insert Genres
INSERT IGNORE INTO genre (id, name)
VALUES
    (1, 'Technology'),
    (2, 'Nature'),
    (3, 'Rock'),
    (4, 'Pop'),
    (5, 'Jazz'),
    (6, 'Classical');

-- Insert Media (now using genre_id to refer to the genre table)
INSERT IGNORE INTO media (id, title, media_type, genre_id, release_date, url, duration, user_id)
VALUES
    (1, 'Tech Talk Episode 1', 'Podcast', 1, '2023-01-01', 'http://example.com/tech-talk-1', '30:00', 1),  -- Kalle, Technology
    (2, 'Planet Earth II Episode 1', 'Documentary', 2, '2016-11-06', 'http://example.com/planet-earth-2-1', '60:00', 2),  -- Lisa, Nature
    (3, 'Come Together', 'Song', 3, '1969-09-26', 'http://example.com/come-together', '4:20', 3),  -- Erik, Rock
    (4, 'Billie Jean', 'Song', 4, '1982-11-30', 'http://example.com/billie-jean', '4:54', 4),  -- Anna, Pop
    (5, 'So What', 'Song', 5, '1959-08-17', 'http://example.com/so-what', '9:22', NULL),  -- No user, Jazz
    (6, 'Beethoven Symphony No. 9', 'Song', 6, '1824-05-07', 'http://example.com/beethoven-9', '65:00', NULL),  -- No user, Classical

    -- Additional Media Entries for Genre Coverage
    (7, 'Jazz Vibes Episode 2', 'Podcast', 5, '2022-06-08', 'http://example.com/jazz-vibes-2', '40:00', NULL),  -- Jazz genre
    (8, 'The Future of Tech II', 'Podcast', 1, '2023-03-01', 'http://example.com/future-tech-2', '35:00', NULL),  -- Technology genre
    (9, 'Nature Wonders', 'Documentary', 2, '2018-01-05', 'http://example.com/nature-wonders', '50:00', NULL),  -- Nature genre
    (10, 'Rock Legends Vol 2', 'Music', 3, '1997-08-22', 'http://example.com/rock-legends-2', '3:50', NULL),  -- Rock genre
    (11, 'Pop Classics', 'Music', 4, '2021-09-30', 'http://example.com/pop-classics', '3:20', NULL),  -- Pop genre
    (12, 'Classical Gems', 'Music', 6, '1805-01-01', 'http://example.com/classical-gems', '80:00', NULL),  -- Classical genre

    -- New media entries to ensure diversity
    (13, 'Technology Insights', 'Podcast', 1, '2023-04-05', 'http://example.com/tech-insights', '25:00', NULL),  -- Technology genre
    (14, 'The Nature Chronicles', 'Documentary', 2, '2021-07-12', 'http://example.com/nature-chronicles', '45:00', NULL),  -- Nature genre
    (15, 'Rock Legends Vol 3', 'Music', 3, '1999-10-10', 'http://example.com/rock-legends-3', '4:10', NULL),  -- Rock genre
    (16, 'Pop Forever', 'Music', 4, '2022-11-18', 'http://example.com/pop-forever', '3:30', NULL),  -- Pop genre
    (17, 'Jazz Beats', 'Podcast', 5, '2022-02-01', 'http://example.com/jazz-beats', '38:00', NULL),  -- Jazz genre
    (18, 'Classical Elegance', 'Music', 6, '1810-12-20', 'http://example.com/classical-elegance', '90:00', NULL),  -- Classical genre
    (19, 'Tech Innovators', 'Podcast', 1, '2023-05-15', 'http://example.com/tech-innovators', '30:00', NULL),  -- Technology genre
    (20, 'Exploring Nature', 'Documentary', 2, '2021-05-20', 'http://example.com/exploring-nature', '55:00', NULL);  -- Nature genre


-- Insert PlaybackHistory (track media users have played)
INSERT IGNORE INTO playback_history (id, user_id, media_id, played_at)
VALUES
    (1, 1, 1, '2023-01-01T10:30:00'),  -- Kalle listened to Tech Talk Episode 1
    (2, 2, 2, '2023-01-02T12:00:00'),  -- Lisa listened to Planet Earth II Episode 1
    (3, 3, 3, '2023-01-03T12:30:00'),  -- Erik listened to Come Together
    (4, 4, 4, '2023-01-04T14:00:00'),  -- Anna listened to Billie Jean
    (5, 1, 8, '2023-03-05T10:00:00'),  -- Kalle listened to The Future of Tech II
    (6, 2, 9, '2023-03-15T12:00:00'),  -- Lisa listened to Nature Wonders
    (7, 3, 10, '2023-02-10T16:00:00'), -- Erik listened to Rock Legends Vol 2
    (8, 4, 12, '2023-04-01T10:30:00'); -- Anna listened to Classical Masterpieces

-- Insert Ratings (user feedback on media)
INSERT IGNORE INTO ratings (id, user_id, media_id, rating)
VALUES
    (1, 1, 1, 'UP'),  -- Kalle liked Tech Talk Episode 1
    (2, 2, 2, 'DOWN'),  -- Lisa disliked Planet Earth II Episode 1
    (3, 3, 3, 'UP'),  -- Erik liked Come Together
    (4, 4, 4, 'DOWN'),  -- Anna disliked Billie Jean
    (5, 1, 5, 'UP'),  -- Kalle liked So What
    (6, 2, 6, 'UP'),  -- Lisa liked Beethoven Symphony No. 9
    (7, 3, 10, 'UP'), -- Erik liked Rock Legends Vol 2
    (8, 4, 12, 'UP'); -- Anna liked Classical Masterpieces


-- Insert Top Media (track media popularity by play count)
INSERT IGNORE INTO top_media (id, user_id, media_id, play_count)
VALUES
    (1, 1, 1, 5),  -- Tech Talk Episode 1 is popular with Kalle (5 plays)
    (2, 2, 2, 3),  -- Planet Earth II Episode 1 is moderately popular with Lisa
    (3, 3, 3, 7),  -- Come Together is popular with Erik (7 plays)
    (4, 4, 4, 2),  -- Billie Jean is slightly popular with Anna
    (5, 1, 8, 4),  -- The Future of Tech II is also popular with Kalle (4 plays)
    (6, 2, 9, 5),  -- Nature Wonders is popular with Lisa (5 plays)
    (7, 3, 10, 6), -- Rock Legends Vol 2 is very popular with Erik (6 plays)
    (8, 4, 12, 3); -- Classical Masterpieces is moderately popular with Anna


-- Insert Recommendations (based on playback history and top media)
INSERT IGNORE INTO recommendation (id, user_id, media_id)
VALUES
    (1, 1, 2),  -- Recommend Planet Earth II Episode 1 to Kalle (unlistened)
    (2, 2, 1),  -- Recommend Tech Talk Episode 1 to Lisa (unlistened)
    (3, 3, 4),  -- Recommend Billie Jean to Erik (unlistened)
    (4, 4, 3),  -- Recommend Come Together to Anna (unlistened)
    (5, 1, 5),  -- Recommend So What to Kalle for genre diversity (Jazz)
    (6, 2, 6),  -- Recommend Beethoven Symphony No. 9 to Lisa for genre diversity (Classical)
    (7, 1, 7),  -- Additional recommendation for Kalle
    (8, 2, 8),  -- Additional recommendation for Lisa
    (9, 3, 9),  -- Additional recommendation for Erik
    (10, 4, 10), -- Additional recommendation for Anna
    (11, 1, 13), -- Recommend Technology Insights to Kalle for variety
    (12, 2, 14), -- Recommend The Nature Chronicles to Lisa for variety
    (13, 3, 15), -- Recommend Rock Legends Vol 3 to Erik for variety
    (14, 4, 16); -- Recommend Pop Forever to Anna for variety
