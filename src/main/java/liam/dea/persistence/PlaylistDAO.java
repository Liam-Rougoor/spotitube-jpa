package liam.dea.persistence;

import liam.dea.Exceptions.InvalidTokenException;
import liam.dea.Exceptions.DatabaseItemNotFoundException;
import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistsOverview;
import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;

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

    public List<Playlist> getAllPlaylists(String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playlist");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Playlist> playlists = new ArrayList<>();
            while (resultSet.next()) {
                Playlist playlist = getPlaylistByID(resultSet.getInt("id"), token);
                playlist.setOwner(tokenDAO.getUserWithToken(token).equals(playlist.getUser()));
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
                playlist.setUser(playlistSet.getString("owner"));
                playlist.setTracks(getTracksOfPlaylist(id));
                return playlist;
            }
            throw new DatabaseItemNotFoundException("Playlist " + id + " not found");
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
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement deletePlaylistStatement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?");
        ) {
            Playlist playlist = getPlaylistByID(id, token);
            deletePlaylistStatement.setInt(1, id);
            deletePlaylistStatement.execute();
            return playlist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist addPlaylist(Playlist playlist, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO playlist(name, owner) VALUES(?, ?)");
                PreparedStatement lastInsertedIDStatement = connection.prepareStatement("SELECT LAST_INSERT_ID() AS id")
        ) {
            if (!tokenDAO.tokenIsValid(token)) {
                throw new InvalidTokenException();
            }
            String user = tokenDAO.getUserWithToken(token);
            insertStatement.setString(1, playlist.getName());
            insertStatement.setString(2, user);
            insertStatement.execute();
            ResultSet playlistIDRow = lastInsertedIDStatement.executeQuery();
            playlistIDRow.next();
            Playlist addedPlaylist = new Playlist();
            addedPlaylist.setName(playlist.getName());
            addedPlaylist.setId(playlistIDRow.getInt("id"));
            addedPlaylist.setUser(user);
            return addedPlaylist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist editPlaylist(Playlist playlist, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?");
        ) {
            if (!tokenDAO.tokenIsValid(token)) {
                throw new InvalidTokenException();
            }
            updateStatement.setString(1, playlist.getName());
            updateStatement.setInt(2, playlist.getId());
            updateStatement.execute();
            Playlist editedPlaylist = new Playlist();
            editedPlaylist.setId(playlist.getId());
            editedPlaylist.setName(playlist.getName());
            editedPlaylist.setUser(playlist.getUser());
            return editedPlaylist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PlaylistsOverview getPlaylistsOverview(String token){
        return new PlaylistsOverview(getAllPlaylists(token));
    }

//    public PlaylistOverview getPlaylistOverview(Playlist playlist){
//        return new PlaylistOverview(playlist);
//    }

    public TracksOverview addTrack(int playlistId, Track track, String token) {
        try (
                Connection connection = new DatabaseConnectionFactory().createConnection();
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO playlist_track VALUES(?, ?)");
        ) {
            if (!tokenDAO.tokenIsValid(token)) {
                throw new InvalidTokenException();
            }

            Playlist playlist = getPlaylistByID(playlistId, token);
            Track foundTrack = new TrackDAO().getTrackByID(track.getId());

            insertStatement.setInt(1, playlistId);
            insertStatement.setInt(2, foundTrack.getId());
            insertStatement.execute();

            foundTrack.setOfflineAvailable(track.getOfflineAvailable());
            playlist.addTrack(foundTrack);
            return new TracksOverview(playlist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
