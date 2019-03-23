package source.lyric;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import source.database.ConnectionHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

class LyricRepository {

    ObservableList<Lyric> getAllLyrics(String bandName, String albumName, String songName) {
        ObservableList<Lyric> lyrics = FXCollections.observableArrayList();

        if ("All bands".equals(bandName) || bandName == null) {
            bandName = "";
        }
        if ("All albums".equals(albumName) || albumName == null) {
            albumName = "";
        }
        if ("All songs".equals(songName) || songName == null) {
            songName = "";
        }

        String sql = "Select lyric.id, lyric, song_id, song.name as song_name, album.name as album_name, band.name as band_name\n" +
                "from lyric\n" +
                "inner join song on song_id = song.id\n" +
                "inner join album on album_id = album.id\n" +
                "inner join band on band_id = band.id\n" +
                "where album.name like '%" + albumName + "%'\n" +
                "and band.name like '%" + bandName + "%'\n" +
                "and song.name like '%" + songName + "%'";

        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                Lyric lyric = new Lyric();
                lyric.setId(resultSet.getInt("id"));
                lyric.setLyric(resultSet.getString("lyric"));
                lyric.setSongId(resultSet.getInt("song_id"));
                lyric.setSongName(resultSet.getString("song_name"));
                lyric.setAlbumName(resultSet.getString("album_name"));
                lyric.setBandName(resultSet.getString("band_name"));
                lyrics.add(lyric);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lyrics;
    }

    void addLyric(Lyric lyric) {
        String sql = String.format("Insert into lyric (lyric, song_id) values ('%s', %d)"
                , lyric.getLyric(), lyric.getSongId());
        ConnectionHelper.executeUpdate(sql);
    }

    void updateLyric(Lyric lyric) {
        String sql = String.format("Update lyric Set lyric = '%s' where id = %d"
                , lyric.getLyric(), lyric.getId());
        ConnectionHelper.executeUpdate(sql);
    }

    void deleteLyric(int lyricId) {
        String sql = "Delete from lyric where id = " + lyricId;
        ConnectionHelper.executeUpdate(sql);
    }
}
