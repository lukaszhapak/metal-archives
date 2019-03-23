package source.album;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import source.band.BandRepository;

public class AlbumTableController {
    public TableView<Album> albumsTableView;

    private AlbumController parent;
    private BandRepository bandRepository;
    private AlbumRepository albumRepository;

    void setParentController(AlbumController parent) {
        this.parent = parent;
    }

    public void initialize() {
        bandRepository = new BandRepository();
        albumRepository = new AlbumRepository();
    }

    public void refresh() {
        ObservableList<String> bandNames = bandRepository.getBandNames();
        bandNames.add(0, "All bands");
        parent.topBarController.bandNameComboBox.setItems(bandNames);
        albumsTableView.setItems(albumRepository
                .getAllAlbums(parent
                        .topBarController
                        .bandNameComboBox
                        .getSelectionModel()
                        .getSelectedItem()));
        parent.formController.closeAlbumForm();
    }
}
