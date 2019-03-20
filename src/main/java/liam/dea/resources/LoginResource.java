package liam.dea.resources;

import liam.dea.dataobjects.Login;
import liam.dea.dataobjects.User;
import liam.dea.persistence.TokenDAO;
import liam.dea.persistence.UserDAO;
import liam.dea.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private LoginService loginService;

    public LoginResource(){}

    @Inject
    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        Login login = loginService.getLogin(user.getUser(), user.getPassword());
        return Response.status(Response.Status.CREATED).entity(login).build();
    }
}



