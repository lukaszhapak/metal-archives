package source.album_per_country;

import javafx.scene.chart.PieChart;
import source.Controller;

public class AlbumPerCountryController extends Controller {


    public PieChart chart;

    private AlbumPerCountryRepository repository;

    public void initialize() {
        repository = new AlbumPerCountryRepository();
    }

    @Override
    public void refresh() {
        chart.setData(repository.getData());
    }
}
