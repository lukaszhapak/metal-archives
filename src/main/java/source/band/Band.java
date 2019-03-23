package source.band;

import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Band {

    private int id;
    private String name;
    private String country;
    private int formedIn;
    private String albums;
    private String genresString;
    private ObservableList<String> genres;
}
