package source.lyric;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import source.Controller;

public class LyricController extends Controller {

    public Pane topBar;
    public Pane table;
    public Pane form;

    public LyricTopBarController topBarController;
    public LyricTableController tableController;
    public LyricFormController formController;

    public Pane topBarBackground;
    public Pane tableBackground;
    public Pane formBackground;
    public Button openFormButton;

    public void initialize() {
        topBarController.setParentController(this);
        tableController.setParentController(this);
        formController.setParentController(this);
    }

    @Override
    public void refresh() {
        tableController.refresh();
    }

    void showForm() {
        topBarBackground.setVisible(false);
        tableBackground.setVisible(false);
        formBackground.setVisible(true);
    }

    void hideForm() {
        topBarBackground.setVisible(true);
        tableBackground.setVisible(true);
        formBackground.setVisible(false);
    }

    public void showAddLyricForm() {
        formController.showAddLyricForm();
    }
}
