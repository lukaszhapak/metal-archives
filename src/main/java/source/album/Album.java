package source.album;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album {

    private int id;
    private String name;
    private int releaseYear;
    private int bandId;
    private String bandName;
    private int songsCount;
}
