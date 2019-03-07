package source.band;

import javafx.scene.layout.Pane;
import source.Controller;

public class BandController extends Controller {

    public Pane topBar;
    public Pane table;
    public Pane form;

    public BandTopBarController topBarController;
    public BandTableController tableController;
    public BandFormController formController;

    public void initialize() {
        topBarController.setParentController(this);
        tableController.setParentController(this);
        formController.setParentController(this);
        refresh();
    }

    @Override
    public void refresh() {
        tableController.refresh();
    }
}
