package source.lyric;

import javafx.scene.control.ComboBox;
import source.alert.AlertHelper;

public class LyricTopBarController {

    public ComboBox<String> albumNameComboBox;
    public ComboBox<String> bandNameComboBox;
    public ComboBox<String> songNameComboBox;

    private LyricController parent;
    private AlertHelper alertHelper;
    private LyricRepository lyricRepository;

    void setParentController(LyricController parent) {
        this.parent = parent;
    }

    public void initialize() {
        lyricRepository = new LyricRepository();
        alertHelper = new AlertHelper();
        addBandNameComboBoxOnChangeListener();
        addAlbumNameComboBoxOnChangeListener();
        addSongNameComboBoxOnChangeListener();
    }

    public void showEditLyricForm() {
        parent.formController.showEditLyricForm();
    }

    public void deleteLyric() {
        if (parent.tableController.lyricsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {
            Lyric selectedLyric = parent.tableController
                    .lyricsTableView
                    .getSelectionModel()
                    .getSelectedItem();
            lyricRepository.deleteLyric(selectedLyric
                    .getId());
            parent.refresh();
        } else {
            alertHelper.displayAlert("Select lyric which u want to delete");
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

    private void addSongNameComboBoxOnChangeListener() {
        songNameComboBox.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> parent.refresh());
    }
}
