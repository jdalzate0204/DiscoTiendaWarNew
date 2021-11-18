package co.edu.unicundi.discotiendawar.exception;

import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

/**
 *
 * @author acer
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Context private UriInfo uriInfo;
    
    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();
        ExceptionWraper ew;
        
        if (e instanceof ResourceIllegalArgumentException | e instanceof IllegalArgumentException) { //400
            ew = new ExceptionWraper(Response.Status.BAD_REQUEST.getStatusCode(),
                                      Response.Status.BAD_REQUEST.getReasonPhrase(),
                                      e.getMessage(),
                                      uriInfo.getPath());
            return Response.status(Response.Status.BAD_REQUEST).entity(ew).build();
            
        } if (e instanceof CloneNotSupportedException ) { //409
            ew = new ExceptionWraper(Response.Status.CONFLICT.getStatusCode(), 
                                      Response.Status.CONFLICT.getReasonPhrase(), 
                                      e.getMessage(), 
                                      uriInfo.getPath());
            return Response.status(Response.Status.CONFLICT).entity(ew).build();  
            
        } else {
            ew = new ExceptionWraper(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 
                                      Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                                      "", 
                                      uriInfo.getPath());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ew).build();
        }
    }
    
}
