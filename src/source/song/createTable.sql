CREATE TABLE song (
    id INT AUTO_INCREMENT,
    name VARCHAR(64),
    duration DECIMAL(4 , 2 ),
    album_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (album_id)
        REFERENCES album (id)
        ON DELETE CASCADE
);