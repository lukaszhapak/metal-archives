CREATE TABLE album (
    id INT AUTO_INCREMENT,
    name VARCHAR(64),
    release_year INT,
    band_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (band_id)
        REFERENCES band (id)
         ON DELETE CASCADE
);