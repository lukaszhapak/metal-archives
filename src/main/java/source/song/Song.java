package source.song;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Song {

    private int id;
    private String name;
    private double duration;
    private int albumId;
    private String bandName;
    private String albumName;
}
