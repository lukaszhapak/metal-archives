package source.album;

import javafx.scene.layout.Pane;
import source.Controller;

public class AlbumController extends Controller {

    public Pane topBar;
    public Pane table;
    public Pane form;

    public AlbumTopBarController topBarController;
    public AlbumTableController tableController;
    public AlbumFormController formController;

    public void initialize() {
        topBarController.setParentController(this);
        tableController.setParentController(this);
        formController.setParentController(this);
    }

    @Override
    public void refresh() {
        tableController.refresh();
    }
}
