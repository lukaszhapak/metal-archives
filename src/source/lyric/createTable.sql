CREATE TABLE lyric (
    id INT AUTO_INCREMENT,
    song_id INT UNIQUE,
    lyric VARCHAR(5250),
    PRIMARY KEY (id),
    FOREIGN KEY (song_id)
        REFERENCES song (id)
        ON DELETE CASCADE
);