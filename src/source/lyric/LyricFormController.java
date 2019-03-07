package source.lyric;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import source.album.AlbumRepository;
import source.alert.AlertHelper;
import source.band.BandRepository;
import source.song.SongRepository;

public class LyricFormController {

    public Pane lyricForm;
    public Button addButton;
    public Label formLabel;
    public Button confirmEditButton;
    public ComboBox<String> newLyricBandName;
    public ComboBox<String> newLyricAlbumName;
    public ComboBox<String> newLyricSongName;
    public TextArea newLyrics;
    public Pane lyricFormTop;

    private LyricController parent;
    private LyricValidation validation;
    private LyricRepository lyricRepository;
    private BandRepository bandRepository;
    private AlertHelper alertHelper;
    private AlbumRepository albumRepository;
    private SongRepository songRepository;
    private Button openFormButton;

    void setParentController(LyricController parent) {
        this.parent = parent;
        openFormButton = parent.openFormButton;
    }

    public void initialize() {
        validation = new LyricValidation(this);
        bandRepository = new BandRepository();
        alertHelper = new AlertHelper();
        albumRepository = new AlbumRepository();
        lyricRepository = new LyricRepository();
        songRepository = new SongRepository();
        addNewLyricBandOnChangeListener();
        addNewLyricAlbumOnChangeListener();
        addNewLyricSongOnChangeListener();
        newLyrics.setWrapText(true);
    }

    public void closeLyricForm() {
        lyricForm.setVisible(false);
        openFormButton.setVisible(true);
        parent.hideForm();
    }

    public void addNewLyric() {
        String selectedSong = newLyricSongName.getSelectionModel().getSelectedItem();

        if (validation.isInputValid()) {
            if (songRepository.songAlreadyHaveLyrics(selectedSong)) {
                alertHelper.displayAlert("This song already have lyrics");
            } else {
                Lyric lyric = new Lyric();
                lyric.setLyric(newLyrics.getText());
                lyric.setSongId(songRepository.getSongId(selectedSong));

                lyricRepository.addLyric(lyric);
                parent.refresh();
            }
        }
    }

    public void updateLyric() {
        if (!"".equals(newLyrics.getText())) {
            Lyric lyric = parent.tableController
                    .lyricsTableView
                    .getSelectionModel()
                    .getSelectedItem();

            lyric.setLyric(newLyrics.getText());
            lyric.setSongId(songRepository.getSongId(newLyricSongName.getSelectionModel().getSelectedItem()));

            lyricRepository.updateLyric(lyric);
            parent.refresh();
        } else {
            alertHelper.displayAlert("Enter Lyrics");
        }
    }

    void showAddLyricForm() {
        lyricFormTop.setVisible(true);
        formLabel.setText("Add new Lyric");
        addButton.setVisible(true);
        confirmEditButton.setVisible(false);
        showForm();
    }

    void showEditLyricForm() {
        if (parent.tableController.lyricsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {

            lyricFormTop.setVisible(false);
            showForm();

            Lyric selectedLyric = parent.tableController
                    .lyricsTableView
                    .getSelectionModel()
                    .getSelectedItem();

            newLyrics.setText(selectedLyric.getLyric());

            formLabel.setText("Edit Lyric");
            addButton.setVisible(false);
            confirmEditButton.setVisible(true);

        } else {
            alertHelper.displayAlert("Select which lyric you want to edit");
        }
    }

    private void showForm() {
        clearInputFields();
        newLyricBandName.setItems(bandRepository.getBandNames());
        openFormButton.setVisible(false);
        lyricForm.setVisible(true);
        parent.showForm();
    }

    private void clearInputFields() {
        newLyricSongName.getSelectionModel().select("");
        newLyricBandName.getSelectionModel().select("");
        newLyricAlbumName.getSelectionModel().select("");
        newLyrics.setText("");
    }

    private void addNewLyricBandOnChangeListener() {
        newLyricBandName.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ov, oldValue, newValue) -> newLyricAlbumName
                                .setItems(albumRepository
                                        .getAlbumNames(newValue)));
    }

    private void addNewLyricAlbumOnChangeListener() {
        newLyricAlbumName.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ov, oldValue, newValue) -> newLyricSongName
                                .setItems(songRepository
                                        .getSongNames(newLyricBandName.getSelectionModel()
                                                .getSelectedItem(), newValue)));
    }

    private void addNewLyricSongOnChangeListener() {
        newLyricSongName.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ov, oldValue, newValue) -> {
                            if (songRepository.songAlreadyHaveLyrics(newValue)) {
                                alertHelper.displayAlert("This song already have lyrics");
                            }
                        });
    }
}
