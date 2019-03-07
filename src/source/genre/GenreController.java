package source.genre;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import source.Controller;
import source.alert.AlertHelper;

public class GenreController extends Controller {

    public ListView<String> genresListView;
    public TextField genreNameInput;
    private GenreRepository genreRepository;
    private AlertHelper alertHelper;

    public void initialize() {
        genreRepository = new GenreRepository();
        alertHelper = new AlertHelper();
    }

    @Override
    public void refresh() {
        genresListView.setItems(FXCollections.observableArrayList(genreRepository.getGenresNames()));
    }

    public void addNewGenre() {
        if (!"".equals(genreNameInput.getText())) {
            Genre genre = new Genre();
            genre.setName(genreNameInput.getText());
            genreRepository.addGenre(genre);
            refresh();
            genreNameInput.setText("");
        } else {
            alertHelper.displayAlert("Genre Name Cannot be Empty");
        }
    }

    public void delete() {
        if (genresListView.getSelectionModel().getSelectedItem() != null) {
            String genre = genresListView.getSelectionModel().getSelectedItem();
            genreRepository.deleteGenre(genre);
            refresh();
        } else {
            alertHelper.displayAlert("Select which genre you want to delete");
        }
    }
}
