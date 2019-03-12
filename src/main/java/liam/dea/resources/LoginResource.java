package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.stores.UserStore;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private static Login activeLogin;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        for (User existingUser : UserStore.getInstance().getUsers()) {
            if (existingUser.equals(user)) {
                Login login = new Login(user, "1234-1234-1234");
                activeLogin = login;
                return Response.status(Response.Status.CREATED).entity(login).build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity("Credentials are invalid").build();

    }

    public static Login getActiveLogin() {
        return activeLogin;
    }

}
