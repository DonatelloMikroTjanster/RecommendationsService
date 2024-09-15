
INSERT INTO my_user (id, name) VALUES
(1, 'david.martin'),
(2, 'emma_smith'),
(3, 'frank_jones'),
(4, 'grace.brown'),
(5, 'hannah_johnson');


INSERT INTO genre (id, name) VALUES
(1, 'Rock'),
(2, 'Pop'),
(3, 'Jazz'),
(4, 'Classical'),
(5, 'Electronic'),
(6, 'Hip Hop');


INSERT INTO media (title, user_id, genre_id) VALUES
('Shape of You', 1, 2),      -- Pop
('Blinding Lights', 1, 2),  -- Pop
('Rolling in the Deep', 2, 2), -- Pop
('Uptown Funk', 2, 2),      -- Pop
('Someone Like You', 3, 3), -- Jazz
('So What', 3, 3),          -- Jazz
('Clair de Lune', 4, 4),    -- Classical
('Symphony No. 5', 4, 4),   -- Classical
('Strobe', 5, 5),           -- Electronic
('One More Time', 5, 5);    -- Electronic

-- Insert Recommendations
INSERT INTO recommendation (user_id, media_id, listened) VALUES
(1, 1, TRUE),   -- David Martin har lyssnat på Shape of You
(1, 2, TRUE),   -- David Martin har lyssnat på Blinding Lights
(2, 3, TRUE),   -- Emma Smith har lyssnat på Rolling in the Deep
(2, 4, TRUE),   -- Emma Smith har lyssnat på Uptown Funk
(3, 5, TRUE),   -- Frank Jones har lyssnat på Someone Like You
(3, 6, TRUE),   -- Frank Jones har lyssnat på So What
(4, 7, TRUE),   -- Grace Brown har lyssnat på Clair de Lune
(4, 8, TRUE),   -- Grace Brown har lyssnat på Symphony No. 5
(5, 9, TRUE),   -- Hannah Johnson har lyssnat på Strobe
(5, 10, TRUE);  -- Hannah Johnson har lyssnat på One More Time
