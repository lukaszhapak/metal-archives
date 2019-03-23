package source.lyric;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import source.album.AlbumRepository;
import source.band.BandRepository;
import source.song.SongRepository;

public class LyricTableController {

    public TableView<Lyric> lyricsTableView;

    private LyricController parent;
    private SongRepository songRepository;
    private BandRepository bandRepository;
    private AlbumRepository albumRepository;
    private LyricRepository lyricRepository;

    void setParentController(LyricController parent) {
        this.parent = parent;
    }

    public void initialize() {
        songRepository = new SongRepository();
        bandRepository = new BandRepository();
        albumRepository = new AlbumRepository();
        lyricRepository = new LyricRepository();
    }

    void refresh() {
        ComboBox<String> bandNameComboBox = parent.topBarController.bandNameComboBox;
        ComboBox<String> albumNameComboBox = parent.topBarController.albumNameComboBox;
        ComboBox<String> songNameComboBox = parent.topBarController.songNameComboBox;

        String selectedBand = bandNameComboBox.getSelectionModel().getSelectedItem();

        ObservableList<String> bandNames = bandRepository.getBandNames();
        bandNames.add(0, "All bands");
        bandNameComboBox.setItems(bandNames);

        ObservableList<String> albumNames = albumRepository.getAlbumNames(selectedBand);
        albumNames.add(0, "All albums");
        albumNameComboBox.setItems(albumNames);

        String selectedAlbum = albumNameComboBox.getSelectionModel().getSelectedItem();

        ObservableList<String> songNames = songRepository.getSongNames(selectedBand, selectedAlbum);
        songNames.add(0, "All songs");
        songNameComboBox.setItems(songNames);

        String selectedSong = songNameComboBox.getSelectionModel().getSelectedItem();

        lyricsTableView.setItems(lyricRepository.getAllLyrics(selectedBand, selectedAlbum, selectedSong));

        parent.formController.closeLyricForm();
    }
}
