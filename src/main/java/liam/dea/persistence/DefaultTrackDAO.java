package liam.dea.persistence;

import liam.dea.exceptions.DatabaseItemNotFoundException;
import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class DefaultTrackDAO implements TrackDAO {

    @Override
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

    @Override
    public TracksOverview getPlaylistTracks(int playlistID) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist_track WHERE playlist = ?");
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (resultSet.next()) {
                Track foundTrack = getTrackByID(resultSet.getInt("track"));
                foundTrack.setOfflineAvailable(resultSet.getBoolean("offline_available"));
                tracks.add(foundTrack);
            }
            return createTracksOverview(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksOverview getAvailableTracks(int playlistID) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT id " +
                                "FROM track " +
                                "WHERE id NOT IN (" +
                                "SELECT track " +
                                "FROM playlist_track " +
                                "where playlist = ? " +
                                ") ");
        ) {
            preparedStatement.setInt(1, playlistID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (resultSet.next()) {
                tracks.add(getTrackByID(resultSet.getInt("id")));
            }
            return createTracksOverview(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksOverview addTrack(int playlistId, Track track) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO playlist_track VALUES(?, ?, ?)");
        ) {
            Track foundTrack = getTrackByID(track.getId());
            insertStatement.setInt(1, playlistId);
            insertStatement.setInt(2, foundTrack.getId());
            insertStatement.setBoolean(3, track.getOfflineAvailable());
            insertStatement.execute();
            foundTrack.setOfflineAvailable(track.getOfflineAvailable());
            return getPlaylistTracks(playlistId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksOverview removeTrack(int playlistID, int trackID) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM playlist_track where playlist = ? AND track = ?");
        ) {
            removeStatement.setInt(1, playlistID);
            removeStatement.setInt(2, trackID);
            removeStatement.execute();
            return getPlaylistTracks(playlistID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksOverview createTracksOverview(List<Track> tracks) {
        return new TracksOverview(tracks);
    }
}
