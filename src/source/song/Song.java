package source.song;

public class Song {

    private int id;
    private String name;
    private double duration;
    private int albumId;
    private String bandName;
    private String albumName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    double getDuration() {
        return duration;
    }

    void setDuration(double duration) {
        this.duration = duration;
    }

    int getAlbumId() {
        return albumId;
    }

    void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getAlbumName() {
        return albumName;
    }

    void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
