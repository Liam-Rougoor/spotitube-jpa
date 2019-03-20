package liam.dea.resources;

import liam.dea.dataobjects.Playlist;
import liam.dea.dataobjects.Track;
import liam.dea.dataobjects.TracksOverview;
import liam.dea.persistence.PlaylistDAO;
import liam.dea.persistence.TrackDAO;
import liam.dea.services.TracksOverviewService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TrackResource {

    private TracksOverviewService tracksOverviewService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(TracksOverviewService tracksOverviewService) {
        this.tracksOverviewService = tracksOverviewService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(@QueryParam("forPlaylist") int playlistID, @QueryParam("token") String token){
        TracksOverview tracksOverview = tracksOverviewService.getTracksOverview(playlistID, token);
        return Response.ok(tracksOverview).build();
    }
}
