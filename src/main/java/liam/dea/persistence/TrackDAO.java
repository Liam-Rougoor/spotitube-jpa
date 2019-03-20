package liam.dea.persistence;

import liam.dea.Exceptions.DatabaseItemNotFoundException;
import liam.dea.Exceptions.InvalidTokenException;
import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    private TokenDAO tokenDAO = new TokenDAO();

    public Track getTrackByID(int id) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM track WHERE id = ?");
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Track track = new Track();
                track.setId(id);
                track.setAlbum(resultSet.getString("album"));
                track.setDescription(resultSet.getString("description"));
                track.setDuration((int) resultSet.getDouble("duration"));
                track.setTitle(resultSet.getString("title"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPerformer(resultSet.getString("performer"));
                track.setPublicationDate(resultSet.getString("publication_date"));
                return track;
            }
            throw new DatabaseItemNotFoundException("Track " + id + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Track> getAvailableTracks(int playlistID, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT t.id as id " +
                                "FROM track t " +
                                "LEFT OUTER JOIN playlist_track pt " +
                                "ON t.id = pt.track " +
                                "WHERE pt.playlist <> ?");
        ) {
            if(!tokenDAO.tokenIsValid(token)){
                throw new InvalidTokenException();
            }
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while(resultSet.next()){
                tracks.add(getTrackByID(resultSet.getInt("id")));
            }
            return tracks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TracksOverview createTracksOverview(List<Track> tracks){
        return new TracksOverview(tracks);
    }
}
