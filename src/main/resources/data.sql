
-- Insert Recommendations (based on playback history and top media)
INSERT IGNORE INTO recommendation (id, user_id, media_id, listened, is_recommended)
VALUES
    (1, 1, 2, false, true),  -- Recommend Planet Earth II Episode 1 to Kalle (unlistened)
    (2, 2, 1, false, true),  -- Recommend Tech Talk Episode 1 to Lisa (unlistened)
    (3, 3, 4, false, true),  -- Recommend Billie Jean to Erik (unlistened)
    (4, 4, 3, false, true),  -- Recommend Come Together to Anna (unlistened)
    (5, 1, 5, false, true),  -- Recommend So What to Kalle for genre diversity (Jazz)
    (6, 2, 6, false, true),  -- Recommend Beethoven Symphony No. 9 to Lisa for genre diversity (Classical)
    (7, 1, 7, false, true),  -- Additional recommendation for Kalle
    (8, 2, 8, false, true),  -- Additional recommendation for Lisa
    (9, 3, 9, false, true),  -- Additional recommendation for Erik
    (10, 4, 10, false, true), -- Additional recommendation for Anna
    (11, 1, 13, false, true), -- Recommend Technology Insights to Kalle for variety
    (12, 2, 14, false, true), -- Recommend The Nature Chronicles to Lisa for variety
    (13, 3, 15, false, true), -- Recommend Rock Legends Vol 3 to Erik for variety
    (14, 4, 16, false, true); -- Recommend Pop Forever to Anna for variety