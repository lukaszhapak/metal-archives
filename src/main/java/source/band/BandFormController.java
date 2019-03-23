package source.band;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import source.alert.AlertHelper;
import source.genre.GenreRepository;

public class BandFormController {

    public TextField newBandName;
    public TextField newBandCountry;
    public TextField newBandFormedIn;
    public ListView<String> newBandGenres;
    public ComboBox<String> availableGenres;
    public Pane bandForm;
    public Button openFormButton;
    public Label formLabel;
    public Button addButton;
    public Button confirmEditButton;

    private BandController parent;
    private BandValidation validation;
    private BandRepository bandRepository;
    private GenreRepository genreRepository;
    private AlertHelper alertHelper;
    private Band selectedBand;

    void setParentController(BandController parent) {
        this.parent = parent;
    }

    public void initialize() {
        addAvailableGenresOnChangeListener();
        validation = new BandValidation(this);
        bandRepository = new BandRepository();
        genreRepository = new GenreRepository();
        alertHelper = new AlertHelper();
    }

    public void addNewBand() {
        if (validation.isInputValid()) {
            Band band = new Band();
            readInputFields(band);
            bandRepository.addBand(band);
            parent.refresh();
        }
    }

    public void clearSelectedGenres() {
        newBandGenres.getItems().clear();
    }

    public void closeBandForm() {
        bandForm.setVisible(false);
        openFormButton.setVisible(true);
    }

    public void showAddBandForm() {
        formLabel.setText("Add new Band");
        addButton.setVisible(true);
        confirmEditButton.setVisible(false);
        showForm();
    }

    void showEditBandForm() {
        if (parent.tableController
                .bandsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {

            showForm();
            selectedBand = parent.tableController
                    .bandsTableView
                    .getSelectionModel()
                    .getSelectedItem();

            newBandName.setText(selectedBand.getName());
            newBandCountry.setText(selectedBand.getCountry());
            newBandFormedIn.setText("" + selectedBand.getFormedIn());
            newBandGenres.setItems(FXCollections.observableArrayList(selectedBand.getGenres()));
            formLabel.setText("Edit Band");
            addButton.setVisible(false);
            confirmEditButton.setVisible(true);
        } else {
            alertHelper.displayAlert("Select which band you want to edit");
        }
    }

    public void updateBand() {
        if (validation.isInputValid()) {
            readInputFields(selectedBand);
            bandRepository.updateBand(selectedBand);
            parent.refresh();
        }
    }

    private void showForm() {
        availableGenres.getItems().clear();
        availableGenres.getItems().addAll(genreRepository.getGenresNames());
        clearInputFields();
        openFormButton.setVisible(false);
        bandForm.setVisible(true);
    }

    private void clearInputFields() {
        newBandName.setText("");
        newBandCountry.setText("");
        newBandFormedIn.setText("");
        availableGenres.setValue("");
        clearSelectedGenres();
    }

    private void readInputFields(Band band) {
        band.setName(newBandName.getText());
        band.setCountry(newBandCountry.getText());
        band.setFormedIn(Integer.parseInt(newBandFormedIn.getText()));
        band.setGenres(newBandGenres.getItems());
    }

    private void addAvailableGenresOnChangeListener() {
        availableGenres
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (ov, oldValue, newValue) -> {
                            if (!newBandGenres.getItems().contains(newValue)) {
                                newBandGenres.getItems().add(newValue);
                            }
                        });
    }
}

