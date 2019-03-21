package liam.dea.resources;

import liam.dea.dataobjects.TracksOverview;
import liam.dea.services.PlaylistTracksService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private PlaylistTracksService playlistTracksService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(PlaylistTracksService playlistsTracksService) {
        this.playlistTracksService = playlistsTracksService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableTracksForPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token){
        TracksOverview tracksOverview = playlistTracksService.getAvailableTracksOverview(playlistID, token);
        return Response.ok(tracksOverview).build();
    }
}
