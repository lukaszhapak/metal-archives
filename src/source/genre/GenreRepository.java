package source.genre;

import source.database.ConnectionHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository {

    public List<String> getGenresNames() {
        List<String> genres = new ArrayList<>();

        String sql = "Select name from genre";
        ResultSet resultSet = ConnectionHelper.executeQuery(sql);

        try {
            while (resultSet.next()) {
                genres.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    void addGenre(Genre genre) {
        String sql = String.format("Insert into genre (name) values ('%s')", genre.getName());
        ConnectionHelper.executeUpdate(sql);
    }

    void deleteGenre(String genreName) {
        String sql = "Delete from genre where name = '" + genreName + "'";
        ConnectionHelper.executeUpdate(sql);
    }

    public int getGenreId(String genreName) {
        String sql = "Select id from genre Where name = '" + genreName + "'";

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
}
