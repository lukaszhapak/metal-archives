package source.album;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import source.database.ConnectionHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRepository {

    public ObservableList<String> getAlbumNames(String bandName) {
        ObservableList<String> albums = FXCollections.observableArrayList();
        if ("All bands".equals(bandName) || bandName == null) {
            bandName = "";
        }
        String sql = "Select album.name\n" +
                "from album\n" +
                "inner join band on band.id = band_id\n" +
                "where band.name like '%" + bandName + "%'";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                albums.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public int getAlbumId(String albumName) {
        String sql = "Select id from album where name = '" + albumName + "'";
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

    public String getAlbumName(int albumId) {
        String sql = "Select name from album where id = " + albumId;
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);
        String albumName = "";
        try {
            while (resultSet.next()) {
                albumName = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albumName;
    }

    ObservableList<Album> getAllAlbums(String bandName) {
        ObservableList<Album> albums = FXCollections.observableArrayList();

        if ("All bands".equals(bandName) || bandName == null) {
            bandName = "";
        }

        String sql = "SELECT album.id, album.name, release_year, band_id, band.name AS band_name, count(song.id) as songs_count\n" +
                "FROM album\n" +
                "INNER JOIN band ON band_id = band.id\n" +
                "LEFT JOIN song on album.id = album_id\n" +
                "GROUP BY album.id\n" +
                "HAVING band_name LIKE '%" + bandName + "%'";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                Album album = new Album();
                album.setId(resultSet.getInt("id"));
                album.setName(resultSet.getString("name"));
                album.setReleaseYear(resultSet.getInt("release_year"));
                album.setBandId(resultSet.getInt("band_id"));
                album.setBandName(resultSet.getString("band_name"));
                album.setSongsCount(resultSet.getInt("songs_count"));
                albums.add(album);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    void addAlbum(Album album) {
        String sql = String.format("Insert into album (name, release_year, band_id) values ('%s', %d, %d)"
                , album.getName(), album.getReleaseYear(), album.getBandId());
        ConnectionHelper.executeUpdate(sql);
    }

    void updateAlbum(Album album) {
        String sql = String.format("Update album Set name = '%s', release_year = %d, band_id = %d where id = %d"
                , album.getName(), album.getReleaseYear(), album.getBandId(), album.getId());

        ConnectionHelper.executeUpdate(sql);
    }

    void deleteAlbum(int albumId) {
        String sql = "Delete from album where id = " + albumId;
        ConnectionHelper.executeUpdate(sql);
    }
}


