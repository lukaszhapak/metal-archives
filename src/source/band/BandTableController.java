package source.band;

import javafx.scene.control.TableView;

public class BandTableController {

    public TableView<Band> bandsTableView;

    private BandController parent;
    private BandRepository bandRepository;

    void setParentController(BandController parent) {
        this.parent = parent;
    }

    public void initialize() {
        bandRepository = new BandRepository();
    }

    void refresh() {
        bandsTableView
                .setItems(bandRepository
                        .getAllBands(parent.topBarController.searchBar
                                .getText()));
        parent.formController.closeBandForm();
    }
}
