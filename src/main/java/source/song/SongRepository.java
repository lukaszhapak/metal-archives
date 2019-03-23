package source.song;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import source.database.ConnectionHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongRepository {

    public ObservableList<String> getSongNames(String bandName, String albumName) {
        ObservableList<String> songs = FXCollections.observableArrayList();
        if ("All bands".equals(bandName) || bandName == null) {
            bandName = "";
        }
        if ("All albums".equals(albumName) || albumName == null) {
            albumName = "";
        }

        String sql = "Select song.name\n" +
                "from song\n" +
                "inner join album on album.id = album_id\n" +
                "inner join band on band.id = band_id\n" +
                "where album.name like '%" + albumName + "%'\n" +
                "and band.name like '%" + bandName + "%'";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                songs.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public boolean songAlreadyHaveLyrics(String songName) {
        if ("".equals(songName) || songName == null) {
            return false;
        }
        String sql = "Select count(lyric.id) from lyric\n" +
                "inner join song on song_id = song.id\n" +
                "where song.name = '" + songName + "'";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        int result = 0;

        try {
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result == 1;
    }

    public int getSongId(String songName) {
        String sql = "Select id from song where name = '" + songName + "'";
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

    ObservableList<Song> getAllSongs(String bandName, String albumName) {
        ObservableList<Song> songs = FXCollections.observableArrayList();

        if ("All bands".equals(bandName) || bandName == null) {
            bandName = "";
        }
        if ("All albums".equals(albumName) || albumName == null) {
            albumName = "";
        }

        String sql = "Select song.id, song.name, duration, album_id, album.name as album_name, band.name as band_name\n" +
                "from song\n" +
                "inner join album on album_id = album.id\n" +
                "inner join band on band_id = band.id\n" +
                "where album.name like '%" + albumName + "%' and band.name like '%" + bandName + "%'";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                Song song = new Song();
                song.setId(resultSet.getInt("id"));
                song.setName(resultSet.getString("name"));
                song.setDuration(resultSet.getDouble("duration"));
                song.setAlbumId(resultSet.getInt("album_id"));
                song.setAlbumName(resultSet.getString("album_name"));
                song.setBandName(resultSet.getString("band_name"));
                songs.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    void addSong(Song song) {
        String sql = String.format("Insert into song (name, duration, album_id) values ('%s', %f, %d)"
                , song.getName(), song.getDuration(), song.getAlbumId());
        ConnectionHelper.executeUpdate(sql);
    }

    void updateSong(Song song) {
        String sql = String.format("Update song Set name = '%s', album_id = %d, duration = %f where id = %d"
                , song.getName(), song.getAlbumId(), song.getDuration(), song.getId());
        ConnectionHelper.executeUpdate(sql);
    }

    void deleteSong(int songId) {
        String sql = "Delete from song where id = " + songId;
        ConnectionHelper.executeUpdate(sql);
    }
}
