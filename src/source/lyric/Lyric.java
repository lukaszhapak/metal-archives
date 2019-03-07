package source.lyric;

public class Lyric {

    private int id;
    private int songId;
    private String lyric;
    private String albumName;
    private String bandName;
    private String songName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int getSongId() {
        return songId;
    }

    void setSongId(int songId) {
        this.songId = songId;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getAlbumName() {
        return albumName;
    }

    void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getSongName() {
        return songName;
    }

    void setSongName(String songName) {
        this.songName = songName;
    }
}
