package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.persistence.PlaylistDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token){
        //TODO: DRY
//        if (!token.equals(LoginResource.getActiveLogin().getToken())) {
//            return Response.status(Response.Status.FORBIDDEN).entity("Invalid token").build();
//        }
        Playlist playlist = playlistDAO.getPlaylistByID(playlistID);
        if(playlist == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Playlist not found").build();
        }
        return Response.ok(playlist.getTracks()).build();
        //return Response.ok(TrackStore.getInstance().getTracks()).build();
    }
}
