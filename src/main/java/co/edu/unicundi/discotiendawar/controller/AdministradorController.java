package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.TokenDto;
import co.edu.unicundi.discotiendajar.entity.Administrador;
import co.edu.unicundi.discotiendajar.exception.*;
import co.edu.unicundi.discotiendajar.service.IAdministradorService;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author ALEJANDRA
 */
@Stateless
@Path("/auth")
public class AdministradorController {
    
    @EJB
    private IAdministradorService service;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Administrador administrador) throws NoAutorizationException {
        TokenDto token = this.service.login(administrador);
        return Response.status(Response.Status.OK).entity(token).build();
    }
    
    @DELETE
    @Path("/cerrarSesion/{idAdmin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("idAdmin")Integer id) throws ResourceNotFoundException {
        this.service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
