package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistOverview;
import liam.dea.persistence.PlaylistDAO;
import liam.dea.dataobjects.PlaylistsOverview;
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
        String user = tokenDAO.getUserWithToken(token);
        PlaylistsOverview overview = new PlaylistsOverview(playlistDAO.getAllPlaylists(user, token));
        return Response.ok(overview).build();
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        Playlist playlist = playlistDAO.getPlaylistByID(id, token);
        return Response.ok(new PlaylistOverview(playlist)).build();
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
        String user = tokenDAO.getUserWithToken(token);
        PlaylistsOverview overview = new PlaylistsOverview(playlistDAO.getAllPlaylists(user, token));
        return Response.status(Response.Status.CREATED).entity(overview).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist){
        playlistDAO.editPlaylist(playlist, token);
        return getAllPlaylists(token);
    }

}
