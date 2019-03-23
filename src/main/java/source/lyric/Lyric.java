package source.lyric;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lyric {

    private int id;
    private int songId;
    private String lyric;
    private String albumName;
    private String bandName;
    private String songName;
}
