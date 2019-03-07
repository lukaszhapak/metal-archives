package source.album_per_country;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import source.database.ConnectionHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

class AlbumPerCountryRepository {

    ObservableList<PieChart.Data> getData() {
        String sql = "select country, count(album.id) as albums_count from band\n" +
                "inner join album on band_id = band.id\n" +
                "group by country\n" +
                "order by albums_count desc";

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                String country = resultSet.getString("country");
                int albumsCount = resultSet.getInt("albums_count");

                data.add(new PieChart.Data(country + " (" + albumsCount + ")", albumsCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
