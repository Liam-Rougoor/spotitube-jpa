package liam.dea.persistence;

import liam.dea.Exceptions.InvalidTokenException;
import liam.dea.Exceptions.PlaylistNotFoundException;
import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    //    public List<Playlist> getPlaylistsOfUser(String username) {
//        try (
//                Connection connection = new DatabaseConnectionFactory().createConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_playlist WHERE user_playlist.user = ?");
//        ) {
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Playlist> playlists = new ArrayList<>();
//            while(resultSet.next()){
//                Playlist playlist = getPlaylistByID(resultSet.getInt("playlist"));
//                playlist.setOwner(username.equals(playlist.getUser()));
//                playlists.add(playlist);
//            }
//            return  playlists;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private TokenDAO tokenDAO = new TokenDAO();

    public List<Playlist> getAllPlaylists(String activeUser, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Playlist> playlists = new ArrayList<>();
            while (resultSet.next()) {
                Playlist playlist = getPlaylistByID(resultSet.getInt("id"), token);
                playlist.setOwner(activeUser.equals(playlist.getUser()));
                playlists.add(playlist);
            }
            return playlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist getPlaylistByID(int id, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement playlistStatement = connection.prepareStatement("SELECT * FROM playlist WHERE id = ?");
                PreparedStatement playlistTrackStatement = connection.prepareStatement("SELECT * FROM playlist_track WHERE playlist = ?");
        ) {
            if (!tokenDAO.tokenIsValid(token)) {
                throw new InvalidTokenException();
            }

            playlistStatement.setInt(1, id);
            ResultSet playlistSet = playlistStatement.executeQuery();

            if (playlistSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(id);
                playlist.setName(playlistSet.getString("name"));
                UserDAO userDAO = new UserDAO();
                playlist.setUser(playlistSet.getString("owner"));
                playlist.setTracks(getTracksOfPlaylist(id));
                return playlist;
            }
            throw new PlaylistNotFoundException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Track> getTracksOfPlaylist(int id) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement playlistTrackStatement = connection.prepareStatement("SELECT * FROM playlist_track WHERE playlist = ?");
        ) {
            playlistTrackStatement.setInt(1, id);
            ResultSet playlistTracks = playlistTrackStatement.executeQuery();
            List<Track> tracks = new ArrayList<>();
            TrackDAO trackDAO = new TrackDAO();
            while (playlistTracks.next()) {
                tracks.add(trackDAO.getTrackByID(playlistTracks.getInt("track")));
            }
            return tracks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist deletePlaylist(int id, String token) {
        Playlist playlist = getPlaylistByID(id, token);
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement deletePlaylistStatement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        ) {
            deletePlaylistStatement.setInt(1, id);
            deletePlaylistStatement.execute();
            return playlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
