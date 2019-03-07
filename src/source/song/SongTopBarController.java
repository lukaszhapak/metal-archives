package source.song;

import javafx.scene.control.ComboBox;
import source.alert.AlertHelper;

public class SongTopBarController {

    public ComboBox<String> albumNameComboBox;
    public ComboBox<String> bandNameComboBox;

    private SongController parent;
    private SongRepository songRepository;
    private AlertHelper alertHelper;

    void setParentController(SongController parent) {
        this.parent = parent;
    }

    public void initialize() {
        songRepository = new SongRepository();
        alertHelper = new AlertHelper();
        addAlbumNameComboBoxOnChangeListener();
        addBandNameComboBoxOnChangeListener();
    }

    public void showEditSongForm() {
        parent.formController.showEditSongForm();
    }

    public void deleteSong() {
        if (parent.tableController.songsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {
            Song selectedSong = parent.tableController.songsTableView
                    .getSelectionModel()
                    .getSelectedItem();
            songRepository
                    .deleteSong(selectedSong
                            .getId());
            parent.refresh();
        } else {
            alertHelper.displayAlert("Select song which u want to delete");
        }
    }

    private void addBandNameComboBoxOnChangeListener() {
        bandNameComboBox.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> parent.refresh());
    }

    private void addAlbumNameComboBoxOnChangeListener() {
        albumNameComboBox.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> parent.refresh());
    }
}
