package source.song;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import source.album.AlbumRepository;
import source.alert.AlertHelper;
import source.band.BandRepository;

public class SongFormController {

    public Button openFormButton;
    public Pane songForm;
    public Button addButton;
    public Label formLabel;
    public Button confirmEditButton;
    public ComboBox<String> newSongBandName;
    public ComboBox<String> newSongAlbumName;
    public TextField newSongName;
    public TextField newSongDuration;

    private SongController parent;
    private SongValidation validation;
    private SongRepository songRepository;
    private BandRepository bandRepository;
    private AlertHelper alertHelper;
    private AlbumRepository albumRepository;
    private Song selectedSong;

    void setParentController(SongController parent) {
        this.parent = parent;
    }

    public void initialize() {
        validation = new SongValidation(this);
        songRepository = new SongRepository();
        bandRepository = new BandRepository();
        alertHelper = new AlertHelper();
        albumRepository = new AlbumRepository();
        addNewSongBandOnChangeListener();
    }

    public void showAddSongForm() {
        formLabel.setText("Add new Song");
        addButton.setVisible(true);
        confirmEditButton.setVisible(false);
        showForm();
    }

    public void closeSongForm() {
        songForm.setVisible(false);
        openFormButton.setVisible(true);
    }

    public void addNewSong() {
        if (validation.isInputValid()) {
            Song song = new Song();
            readInputFields(song);

            songRepository.addSong(song);
            parent.refresh();
        }
    }

    public void updateSong() {
        if (validation.isInputValid()) {
            readInputFields(selectedSong);

            songRepository.updateSong(selectedSong);
            parent.refresh();
        }
    }

    void showEditSongForm() {
        if (parent.tableController.songsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {

            showForm();

            selectedSong = parent.tableController
                    .songsTableView
                    .getSelectionModel()
                    .getSelectedItem();

            newSongName.setText(selectedSong.getName());
            newSongDuration.setText("" + selectedSong.getDuration());

            newSongBandName.getSelectionModel()
                    .select(bandRepository
                            .getBandNameByAlbumId(selectedSong
                                    .getAlbumId()));

            newSongAlbumName.getSelectionModel()
                    .select(albumRepository
                            .getAlbumName(selectedSong
                                    .getAlbumId()));

            formLabel.setText("Edit Song");
            addButton.setVisible(false);
            confirmEditButton.setVisible(true);

        } else {
            alertHelper.displayAlert("Select which song you want to edit");
        }
    }

    private void showForm() {
        newSongBandName.setItems(bandRepository.getBandNames());
        openFormButton.setVisible(false);
        songForm.setVisible(true);
        clearInputFields();
    }

    private void clearInputFields() {
        newSongBandName.getSelectionModel().select("");
        newSongAlbumName.getSelectionModel().select("");
        newSongName.setText("");
        newSongDuration.setText("");
    }

    private void addNewSongBandOnChangeListener() {
        newSongBandName.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ov, oldValue, newValue) -> newSongAlbumName
                                .setItems(albumRepository
                                        .getAlbumNames(newValue)));
    }

    private void readInputFields(Song song) {
        song.setAlbumId(albumRepository
                .getAlbumId(newSongAlbumName
                        .getSelectionModel()
                        .getSelectedItem()));
        song.setName(newSongName.getText());
        song.setDuration(Double.parseDouble(newSongDuration.getText()));
    }
}
