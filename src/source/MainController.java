package source;

import javafx.scene.control.TabPane;
import source.album.AlbumController;
import source.album_per_country.AlbumPerCountryController;
import source.band.BandController;
import source.genre.GenreController;
import source.lyric.LyricController;
import source.song.SongController;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    public TabPane tabPanel;
    public BandController bandController;
    public AlbumController albumController;
    public GenreController genreController;
    public SongController songController;
    public LyricController lyricController;
    public AlbumPerCountryController albumPerCountryController;

    private Map<String, Controller> controllers = new HashMap<>();

    public void initialize() {
        controllers.put("Bands", bandController);
        controllers.put("Albums", albumController);
        controllers.put("Songs", songController);
        controllers.put("Lyrics", lyricController);
        controllers.put("Albums per Country", albumPerCountryController);
        controllers.put("Genres", genreController);

        tabPanel.getSelectionModel()
                .selectedItemProperty()
                .addListener((ov, oldTab, newTab) -> {
                    String newTabText = newTab.getText();
                    if (!"Admin Panel".equals(newTabText)) {
                        controllers.get(newTabText).refresh();
                    }
                });
    }
}
