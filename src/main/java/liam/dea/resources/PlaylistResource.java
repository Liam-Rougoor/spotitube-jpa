package liam.dea.resources;

import liam.dea.dataobjects.*;
import liam.dea.persistence.PlaylistDAO;
import liam.dea.persistence.TokenDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private TokenDAO tokenDAO = new TokenDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        return Response.ok(playlistDAO.getPlaylistsOverview(token)).build();
    }

    @Path("{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        playlistDAO.deletePlaylist(id, token);
        return getAllPlaylists(token);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlaylist(@QueryParam("token") String token, Playlist playlist){
        playlistDAO.addPlaylist(playlist, token);
        return Response.status(Response.Status.CREATED).entity(playlistDAO.getPlaylistsOverview(token)).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist){
        playlistDAO.editPlaylist(playlist, token);
        return getAllPlaylists(token);
    }



    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        Playlist playlist = playlistDAO.getPlaylistByID(id, token);
        return Response.ok(new TracksOverview(playlist)).build();
    }

    @Path("{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track){
        TracksOverview tracksOverview = playlistDAO.addTrack(playlistId, track, token);
        return Response.status(Response.Status.CREATED).entity(tracksOverview).build();
    }

}
