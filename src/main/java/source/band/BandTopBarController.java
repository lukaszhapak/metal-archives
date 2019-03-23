package source.band;

import javafx.scene.control.TextField;
import source.alert.AlertHelper;

public class BandTopBarController {

    public TextField searchBar;

    private BandController parent;
    private BandRepository bandRepository;
    private AlertHelper alertHelper;

    void setParentController(BandController parent) {
        this.parent = parent;
    }

    public void initialize() {
        bandRepository = new BandRepository();
        alertHelper = new AlertHelper();
        searchBar.textProperty().addListener(observable -> parent.refresh());
    }

    public void showEditBandForm() {
        parent.formController.showEditBandForm();
    }

    public void deleteBand() {
        if (parent.tableController.bandsTableView
                .getSelectionModel()
                .getSelectedItem() != null) {
            Band selectedBand = parent.tableController.bandsTableView
                    .getSelectionModel()
                    .getSelectedItem();
            bandRepository
                    .deleteBand(selectedBand
                            .getId());
            parent.refresh();
        } else {
            alertHelper.displayAlert("Select band which u want to delete");
        }
    }
}
