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
                                                 ('Don''t Start Now', 1, 2),
                                                 ('Levitating', 1, 2),
                                                 ('Bad Guy', 2, 2),
                                                 ('Watermelon Sugar', 2, 2),
                                                 ('Physical', 1, 2),
                                                 ('Say So', 1, 2),
                                                 ('Happier', 2, 2),
                                                 ('Peaches', 2, 2),
                                                 ('Freddie Freeloader', 3, 3),
                                                 ('Take Five', 3, 3),
                                                 ('My Favorite Things', 3, 3),
                                                 ('All Blues', 3, 3),
                                                 ('Take the A Train', 3, 3),
                                                 ('In a Sentimental Mood', 3, 3),
                                                 ('The Four Seasons: Spring', 4, 4),
                                                 ('Rhapsody in Blue', 4, 4),
                                                 ('Moonlight Sonata', 4, 4),
                                                 ('Hungarian Dance No. 5', 4, 4),
                                                 ('Ave Maria', 4, 4),
                                                 ('Ode to Joy', 4, 4),
                                                 ('Titanium', 5, 5),
                                                 ('Wake Me Up', 5, 5),
                                                 ('Levels', 5, 5),
                                                 ('Animals', 5, 5),
                                                 ('Scary Monsters and Nice Sprites', 5, 5),
                                                 ('One More Time', 5, 5);

INSERT INTO recommendation (user_id, media_id, listened) VALUES
                                                             (1, 1, TRUE),
                                                             (1, 2, TRUE),
                                                             (2, 3, TRUE),
                                                             (2, 4, TRUE),
                                                             (3, 5, TRUE),
                                                             (3, 6, TRUE),
                                                             (4, 7, TRUE),
                                                             (4, 8, TRUE),
                                                             (5, 9, TRUE),
                                                             (5, 10, TRUE),
                                                             (1, 3, FALSE),
                                                             (1, 4, FALSE),
                                                             (2, 1, FALSE),
                                                             (2, 5, FALSE),
                                                             (3, 7, FALSE),
                                                             (3, 8, FALSE),
                                                             (4, 9, FALSE),
                                                             (4, 10, FALSE),
                                                             (5, 11, FALSE),
                                                             (5, 12, FALSE);