package liam.dea.resources;

import liam.dea.dataobjects.TracksOverview;
import liam.dea.services.PlaylistTracksService;
import liam.dea.services.TokenService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Transactional
@Path("/tracks")
public class TrackResource {

    private PlaylistTracksService playlistTracksService;
    private TokenService tokenService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(PlaylistTracksService playlistsTracksService, TokenService tokenService) {
        this.playlistTracksService = playlistsTracksService;
        this.tokenService = tokenService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableTracksForPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token) {
        tokenService.validateToken(token);
        TracksOverview tracksOverview = playlistTracksService.getAvailableTracksOverview(playlistID);
        return Response.ok(tracksOverview).build();
    }
}
