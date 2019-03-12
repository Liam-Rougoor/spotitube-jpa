package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.PlaylistOverview;
import liam.dea.stores.PlaylistStore;
import liam.dea.dataobjects.PlaylistsOverview;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        //TODO: DRY
        if (!token.equals(LoginResource.getActiveLogin().getToken())) {
            return Response.status(Response.Status.FORBIDDEN).entity("Invalid token").build();
        }
        return Response.ok(new PlaylistsOverview(PlaylistStore.getInstance().getPlaylists())).build();
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        //TODO: DRY
        if (!token.equals(LoginResource.getActiveLogin().getToken())) {
            return Response.status(Response.Status.FORBIDDEN).entity("Invalid token").build();
        }
        Playlist playlist = PlaylistStore.getInstance().getPlaylistByID(id);
        if(playlist == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Playlist not found").build();
        }
        return Response.ok(new PlaylistOverview(playlist)).build();
    }
}
