package source.album;

public class Album {

    private int id;
    private String name;
    private int releaseYear;
    private int bandId;
    private String bandName;
    private int songsCount;

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

    int getReleaseYear() {
        return releaseYear;
    }

    void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    int getBandId() {
        return bandId;
    }

    void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getSongsCount() {
        return songsCount;
    }

    void setSongsCount(int songsCount) {
        this.songsCount = songsCount;
    }
}
