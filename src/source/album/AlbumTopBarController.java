package source.album;

import javafx.scene.control.ComboBox;
import source.alert.AlertHelper;

public class AlbumTopBarController {

    public ComboBox<String> bandNameComboBox;
    private AlertHelper alertHelper;
    private AlbumRepository albumRepository;

    private AlbumController parent;

    void setParentController(AlbumController parent) {
        this.parent = parent;
    }

    public void initialize() {
        addBandNameComboBoxOnChangeListener();
        alertHelper = new AlertHelper();
        albumRepository = new AlbumRepository();
    }

    public void deleteAlbum() {
        if (parent.tableController.albumsTableView.getSelectionModel().getSelectedItem() != null) {
            Album selectedAlbum = parent.tableController.albumsTableView.getSelectionModel().getSelectedItem();
            albumRepository.deleteAlbum(selectedAlbum.getId());
            parent.refresh();
        } else {
            alertHelper.displayAlert("Select album which u want to delete");
        }
    }

    public void showEditAlbumForm() {
        parent.formController.showEditAlbumForm();
    }

    private void addBandNameComboBoxOnChangeListener() {
        bandNameComboBox.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> parent.refresh());
    }
}
