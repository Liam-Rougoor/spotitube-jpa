package liam.dea.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseItemNotFoundExceptionMapper implements ExceptionMapper<DatabaseItemNotFoundException> {
    @Override
    public Response toResponse(DatabaseItemNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
