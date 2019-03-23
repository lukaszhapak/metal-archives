package source.band_genre;

import source.database.ConnectionHelper;

public class BandGenreRepository {

    public void deleteRelations(int bandId) {
        String sql = "Delete from band_genre where band_id = " + bandId;
        ConnectionHelper.executeUpdate(sql);
    }

    public void addRelation(int bandId, int genreId) {
        String sql;
        sql = "insert into band_genre (band_id, genre_id) values (" + bandId + ", " + genreId + ")";
        ConnectionHelper.executeUpdate(sql);
    }
}
