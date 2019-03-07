CREATE TABLE band_genre (
    band_id INT,
    genre_id INT,
    FOREIGN KEY (band_id)
        REFERENCES band (id)
        ON DELETE CASCADE,
    FOREIGN KEY (genre_id)
        REFERENCES genre (id)
         ON DELETE CASCADE
);