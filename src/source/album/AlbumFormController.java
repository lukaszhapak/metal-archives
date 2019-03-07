package source.album;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import source.alert.AlertHelper;
import source.band.BandRepository;

public class AlbumFormController {

    public Button openFormButton;
    public Pane albumForm;
    public Button addButton;
    public Label formLabel;
    public Button confirmEditButton;
    public ComboBox<String> newAlbumBandName;
    public TextField newAlbumName;
    public TextField newAlbumReleaseYear;

    private AlbumController parent;
    private AlbumValidation validation;
    private AlbumRepository albumRepository;
    private BandRepository bandRepository;
    private AlertHelper alertHelper;
    private Album selectedAlbum;

    void setParentController(AlbumController parent) {
        this.parent = parent;
    }

    public void initialize() {
        validation = new AlbumValidation(this);
        albumRepository = new AlbumRepository();
        bandRepository = new BandRepository();
        alertHelper = new AlertHelper();
    }

    public void showAddAlbumForm() {
        formLabel.setText("Add new Album");
        addButton.setVisible(true);
        confirmEditButton.setVisible(false);
        showForm();
    }

    public void closeAlbumForm() {
        albumForm.setVisible(false);
        openFormButton.setVisible(true);
    }

    public void addNewAlbum() {
        if (validation.isInputValid()) {
            Album album = new Album();
            readInputFields(album);

            albumRepository.addAlbum(album);
            parent.refresh();
        }
    }

    public void updateAlbum() {
        if (validation.isInputValid()) {
            readInputFields(selectedAlbum);
            albumRepository.updateAlbum(selectedAlbum);
            parent.refresh();
        }
    }

    void showEditAlbumForm() {
        if (parent.tableController
                .albumsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {

            showForm();
            selectedAlbum = parent.tableController
                    .albumsTableView
                    .getSelectionModel()
                    .getSelectedItem();

            newAlbumName.setText(selectedAlbum.getName());

            newAlbumReleaseYear.setText("" + selectedAlbum.getReleaseYear());

            newAlbumBandName.getSelectionModel()
                    .select(bandRepository
                            .getBandNameById(selectedAlbum
                                    .getBandId()));

            formLabel.setText("Edit Album");
            addButton.setVisible(false);
            confirmEditButton.setVisible(true);

        } else {
            alertHelper.displayAlert("Select which album you want to edit");
        }
    }

    private void readInputFields(Album album) {
        album.setBandId(bandRepository
                .getBandId(newAlbumBandName
                        .getSelectionModel()
                        .getSelectedItem()));

        album.setName(newAlbumName.getText());
        album.setReleaseYear(Integer.parseInt(newAlbumReleaseYear.getText()));
    }

    private void showForm() {
        newAlbumBandName.setItems(bandRepository.getBandNames());
        openFormButton.setVisible(false);
        albumForm.setVisible(true);
        clearInputFields();
    }

    private void clearInputFields() {
        newAlbumBandName.getSelectionModel().select("");
        newAlbumName.setText("");
        newAlbumReleaseYear.setText("");
    }
}
