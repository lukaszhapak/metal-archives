package source.song;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import source.album.AlbumRepository;
import source.band.BandRepository;

public class SongTableController {

    public TableView<Song> songsTableView;

    private SongController parent;
    private SongRepository songRepository;
    private BandRepository bandRepository;
    private AlbumRepository albumRepository;

    void setParentController(SongController parent) {
        this.parent = parent;
    }

    public void initialize() {
        songRepository = new SongRepository();
        bandRepository = new BandRepository();
        albumRepository = new AlbumRepository();
    }

    void refresh() {
        ComboBox<String> bandNameComboBox = parent.topBarController.bandNameComboBox;
        ComboBox<String> albumNameComboBox = parent.topBarController.albumNameComboBox;
        String selectedBand = bandNameComboBox.getSelectionModel().getSelectedItem();

        ObservableList<String> bandNames = bandRepository.getBandNames();
        bandNames.add(0, "All bands");
        bandNameComboBox.setItems(bandNames);

        ObservableList<String> albumNames = albumRepository.getAlbumNames(selectedBand);
        albumNames.add(0, "All albums");
        albumNameComboBox.setItems(albumNames);

        String selectedAlbum = albumNameComboBox.getSelectionModel().getSelectedItem();

        songsTableView
                .setItems(songRepository.getAllSongs(selectedBand, selectedAlbum));

        parent.formController.closeSongForm();
    }
}
