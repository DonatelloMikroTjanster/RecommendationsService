
CREATE TABLE users (
                       id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                       email VARCHAR(255),
                       username VARCHAR(255),
                       PRIMARY KEY (id)
);

CREATE TABLE media (
                       id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                       title VARCHAR(255),
                       mediaCategory VARCHAR(255),
                       PRIMARY KEY (id)
);

CREATE TABLE recommendation (
                                id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                                user_id BIGINT NOT NULL,
                                media_id BIGINT NOT NULL,
                                reason VARCHAR(255),
                                PRIMARY KEY (id),
                                FOREIGN KEY (user_id) REFERENCES users(id),
                                FOREIGN KEY (media_id) REFERENCES media(id)
);


INSERT INTO users (id, username, email) VALUES
                                            (3, 'charlie', 'charlie@example.com'),
                                            (4, 'diana', 'diana@example.com'),
                                            (5, 'edward', 'edward@example.com');

INSERT INTO media (id, title, mediaCategory) VALUES
                                                 (4, 'Imagine', 'MUSIC'),
                                                 (5, 'Podcast of the Year', 'PODCAST'),
                                                 (6, 'Breaking News', 'NEWS'),
                                                 (7, 'The Great Gatsby', 'BOOK');

INSERT INTO recommendation (id, user_id, media_id, reason) VALUES
                                                               (3, 3, 4, 'Based on your interest in classic music'),
                                                               (4, 3, 5, 'Based on your interest in insightful podcasts'),
                                                               (5, 4, 6, 'Based on your interest in current events'),
                                                               (6, 4, 7, 'Based on your interest in classic literature'),
                                                               (7, 5, 4, 'Based on your interest in inspirational music'),
                                                               (8, 5, 5, 'Based on your interest in trending podcasts'),
                                                               (9, 4, 6, 'Based on your interest in daily news updates'),
                                                               (10, 4, 7, 'Based on your interest in literary classics'),
                                                               (11, 5, 7, 'Based on your interest in classic novels'),
                                                               (12, 5, 6, 'Based on your interest in recent news');
