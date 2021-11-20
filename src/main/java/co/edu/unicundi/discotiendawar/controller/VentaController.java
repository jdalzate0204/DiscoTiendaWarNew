package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.entity.Album;
import co.edu.unicundi.discotiendajar.entity.Pago;
import co.edu.unicundi.discotiendajar.service.IAlbumService;
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
    private IAlbumService service2;
    
    @GET
    @Path("/listarPago")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPago() {
        List<Pago> lista = service.obtenerPago();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @GET
    @Path("/listarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAlbumes(){
        List<Album> lista = this.service2.listar();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
}
