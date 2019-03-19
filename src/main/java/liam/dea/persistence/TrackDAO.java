package liam.dea.persistence;

import liam.dea.dataobjects.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDAO {
    public Track getTrackByID(int id) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE id = ?");
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Track track = null;
            if(resultSet.next()){
                track = new Track();
                track.setId(id);
                track.setAlbum("");
                track.setDescription(resultSet.getString("description"));
                track.setDuration((int)resultSet.getDouble("duration"));
                track.setTitle(resultSet.getString("title"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPerformer(resultSet.getString("performer"));
                track.setPublicationDate(resultSet.getString("publication_date"));
                track.setOfflineAvailable(false);
            }
            return track;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
