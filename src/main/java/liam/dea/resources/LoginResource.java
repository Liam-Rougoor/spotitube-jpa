package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;
import liam.dea.persistence.old.UserStore;

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
        UserDAO userDAO = new UserDAO();
        TokenDAO tokenDAO = new TokenDAO();
        if(userDAO.getUserByNameAndPassword(user.getUser(), user.getPassword()) != null) {
            return Response.status(Response.Status.CREATED).entity(tokenDAO.getLogin(user.getUser())).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).entity("Credentials are invalid").build();

    }

    public static Login getActiveLogin() {
        return activeLogin;
    }

}
