package liam.dea;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Home {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response home(){
        return Response.ok("Hello World").build();
    }
}
