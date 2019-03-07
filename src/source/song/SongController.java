package source.song;

import javafx.scene.layout.Pane;
import source.Controller;

public class SongController extends Controller {

    public Pane topBar;
    public Pane table;
    public Pane form;

    public SongTopBarController topBarController;
    public SongTableController tableController;
    public SongFormController formController;

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
