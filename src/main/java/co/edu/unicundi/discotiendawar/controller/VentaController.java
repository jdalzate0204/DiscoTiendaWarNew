package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.entity.Pago;
import co.edu.unicundi.discotiendajar.service.IVentaService;
import java.util.List;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author acer
 */
@Stateless
@Path("/ventas")
public class VentaController {
    
    @EJB
    private IVentaService service;
    
    @GET
    @Path("/listarPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPago() {
        List<Pago> lista = service.obtenerPago();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
}
