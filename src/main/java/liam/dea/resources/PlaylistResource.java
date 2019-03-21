package liam.dea.resources;

import liam.dea.dataobjects.*;
import liam.dea.resources.util.TokenValidator;
import liam.dea.services.PlaylistService;
import liam.dea.services.PlaylistTracksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private PlaylistTracksService playlistTracksService;
    private TokenValidator tokenValidator;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService, PlaylistTracksService playlistTracksService, TokenValidator tokenValidator) {
        this.playlistService = playlistService;
        this.playlistTracksService = playlistTracksService;
        this.tokenValidator = tokenValidator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        tokenValidator.validateToken(token);
        return Response.ok(playlistService.getPlaylistsOverview(tokenValidator.getUserWithToken(token))).build();
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        tokenValidator.validateToken(token);
        playlistService.deletePlaylist(id);
        return getAllPlaylists(token);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(@QueryParam("token") String token, Playlist playlist) {
        tokenValidator.validateToken(token);
        playlistService.createPlaylist(playlist, tokenValidator.getUserWithToken(token));
        return Response.status(Response.Status.CREATED).entity(playlistService.getPlaylistsOverview(tokenValidator.getUserWithToken(token))).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist) {
        tokenValidator.validateToken(token);
        playlistService.editPlaylist(playlist);
        return getAllPlaylists(token);
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        tokenValidator.validateToken(token);
        TracksOverview tracksOverview = playlistTracksService.getPlaylistTracksOverview(id);
        return Response.ok(tracksOverview).build();
    }

    @Path("{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track) {
        tokenValidator.validateToken(token);
        TracksOverview tracksOverview = playlistTracksService.addTrack(playlistId, track);
        return Response.status(Response.Status.CREATED).entity(tracksOverview).build();
    }

    @Path("{playlistID}/tracks/{trackID}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrack(@QueryParam("token") String token, @PathParam("playlistID") int playlistID, @PathParam("trackID") int trackID) {
        tokenValidator.validateToken(token);
        TracksOverview tracksOverview = playlistTracksService.removeTrack(playlistID, trackID);
        return Response.ok(tracksOverview).build();
    }
}
