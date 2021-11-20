package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.TokenDto;
import co.edu.unicundi.discotiendajar.entity.Administrador;
import co.edu.unicundi.discotiendajar.exception.NoAutorizationException;
import co.edu.unicundi.discotiendajar.exception.ResourceNotFoundException;
import co.edu.unicundi.discotiendajar.service.IAdministradorService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ALEJANDRA
 */
@Stateless
@Path("/auth")
public class AdministradorCotroller {
    
    @EJB
    private IAdministradorService service;
    
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Administrador administrador) throws NoAutorizationException{
        TokenDto token=this.service.login(administrador);
        return Response.status(Response.Status.OK).entity(token).build();
    }
    
    @DELETE
    @Path("/cerrarSesion/{idAdmin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("idAdmin")Integer id)throws ResourceNotFoundException{
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
