package source.band;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import source.band_genre.BandGenreRepository;
import source.database.ConnectionHelper;
import source.genre.GenreRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BandRepository {

    private GenreRepository genreRepository;
    private BandGenreRepository bandGenreRepository;

    public BandRepository() {
        genreRepository = new GenreRepository();
        bandGenreRepository = new BandGenreRepository();
    }

    public ObservableList<String> getBandNames() {
        ObservableList<String> bandNames = FXCollections.observableArrayList();
        String sql = "Select name from band";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                bandNames.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandNames;
    }

    public int getBandFormedIn(String bandName) {
        String sql = "Select formed_in from band where name = '" + bandName + "'";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);
        int bandFormedIn = 0;
        try {
            while (resultSet.next()) {
                bandFormedIn = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandFormedIn;
    }

    public int getBandId(String bandName) {
        String sql = "Select id from band where name = '" + bandName + "'";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);
        int id = 0;
        try {
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getBandNameById(int bandId) {
        String sql = "Select name from band where id = " + bandId;
        return getBandName(sql);
    }

    public String getBandNameByAlbumId(int albumId) {
        String sql = "select band.name from band\n" +
                "inner join album on band_id = band.id\n" +
                "where album.id = " + albumId;
        return getBandName(sql);
    }

    ObservableList<Band> getAllBands(String searchText) {
        ObservableList<Band> bands = FXCollections.observableArrayList();

        String sql = "SELECT band.id, band.name, country, formed_in,\n" +
                "GROUP_CONCAT(distinct album.name SEPARATOR ', ') AS albums,\n" +
                "GROUP_CONCAT(distinct genre.name SEPARATOR ', ') AS genres\n" +
                "FROM band\n" +
                "LEFT JOIN album ON band.id = album.band_id\n" +
                "LEFT JOIN band_genre ON band.id = band_genre.band_id\n" +
                "LEFT JOIN genre ON genre_id =  genre.id\n" +
                "WHERE band.name LIKE '%" + searchText + "%' OR country LIKE '%" + searchText + "%'\n" +
                "GROUP BY band.id , band.name , country , formed_in";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                Band band = new Band();
                band.setId(resultSet.getInt("id"));
                band.setName(resultSet.getString("name"));
                band.setCountry(resultSet.getString("country"));
                band.setFormedIn(resultSet.getInt("formed_in"));
                band.setAlbums(resultSet.getString("albums"));
                band.setGenresString(resultSet.getString("genres"));
                if (band.getGenresString() != null) {
                    band.setGenres(FXCollections.observableArrayList(band.getGenresString().split(", ")));
                } else {
                    band.setGenres(FXCollections.observableArrayList());
                }
                bands.add(band);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bands;
    }

    void addBand(Band band) {
        String sql = String.format("Insert into band (name, country, formed_in) values ('%s', '%s', %d)"
                , band.getName(), band.getCountry(), band.getFormedIn());

        ConnectionHelper.executeUpdate(sql);
        band.setId(getBandId(band));

        addGenresRelations(band);
    }

    void updateBand(Band band) {
        String sql = String.format("Update band Set name = '%s', country = '%s', formed_in = '%s' where id = %d"
                , band.getName(), band.getCountry(), band.getFormedIn(), band.getId());

        ConnectionHelper.executeUpdate(sql);

        bandGenreRepository.deleteRelations(band.getId());
        addGenresRelations(band);
    }

    void deleteBand(int bandId) {
        String sql = "Delete from band where id = " + bandId;
        ConnectionHelper.executeUpdate(sql);
    }

    private int getBandId(Band band) {
        String sql = String.format("Select id from band where name = '%s' and country = '%s' and formed_in = %d",
                band.getName(), band.getCountry(), band.getFormedIn());

        int id = 0;

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private void addGenresRelations(Band band) {
        for (String genre : band.getGenres()) {
            int genreId = genreRepository.getGenreId(genre);
            bandGenreRepository.addRelation(band.getId(), genreId);
        }
    }

    private String getBandName(String sql) {
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);
        String bandName = "";
        try {
            while (resultSet.next()) {
                bandName = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bandName;
    }
}
