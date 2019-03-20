package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;
import liam.dea.persistence.PlaylistDAO;
import liam.dea.persistence.TrackDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TrackResource {

    private TrackDAO trackDAO = new TrackDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token){
        List<Track> tracks = trackDAO.getAvailableTracks(playlistID, token);
        return Response.ok(trackDAO.createTracksOverview(tracks)).build();
    }
}
