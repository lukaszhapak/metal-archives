package source.band;

import javafx.collections.ObservableList;

public class Band {

    private int id;
    private String name;
    private String country;
    private int formedIn;
    private String albums;
    private String genresString;
    private ObservableList<String> genres;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    int getFormedIn() {
        return formedIn;
    }

    void setFormedIn(int formedIn) {
        this.formedIn = formedIn;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    String getGenresString() {
        return genresString;
    }

    void setGenresString(String genresString) {
        this.genresString = genresString;
    }

    ObservableList<String> getGenres() {
        return genres;
    }

    void setGenres(ObservableList<String> genres) {
        this.genres = genres;
    }
}
