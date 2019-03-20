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
//        if (!tokenDAO.tokenIsValid(token)) {
//            return Response.status(Response.Status.FORBIDDEN).entity("Invalid token").build();
//        }
        String user = tokenDAO.getUserWithToken(token);
//        if(user==null || user.isEmpty()){
//            throw new RuntimeException();
//        }
        PlaylistsOverview overview = new PlaylistsOverview(playlistDAO.getAllPlaylists(user, token));
        return Response.ok(overview).build();
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
//        if (!tokenDAO.tokenIsValid(token)) {
//            return Response.status(Response.Status.FORBIDDEN).entity("Invalid token").build();
//        }
        Playlist playlist = playlistDAO.getPlaylistByID(id, token);
        if(playlist == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Playlist not found").build();
        }
        return Response.ok(new PlaylistOverview(playlist)).build();
    }

}
