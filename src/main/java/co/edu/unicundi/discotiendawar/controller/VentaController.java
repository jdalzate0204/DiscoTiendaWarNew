package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.VentaDto;
import co.edu.unicundi.discotiendajar.entity.Album;
import co.edu.unicundi.discotiendajar.entity.Carrito;
import co.edu.unicundi.discotiendajar.entity.Pago;
import co.edu.unicundi.discotiendajar.entity.Venta;
import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
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
    
    @GET
    @Path("/listarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCatalogo(){
        List<Album> lista = this.service.listarCatalogo();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @POST
    @Path("/agregarCarrito")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarCarrito(Carrito carrito) {
        this.service.guardarCarrito(carrito);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/listarCarrito")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCarrito(){
        List<Carrito> lista = this.service.listarCarrito();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @POST
    @Path("/guardarHistorial")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(VentaDto venta) throws ResourceIllegalArgumentException, CloneNotSupportedException {
        this.service.guardarHistorial(venta);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    @Path("/editarEstado")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarEstado(Boolean estado) {
        this.service.editar(estado);
        return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Path("/listarPago/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPagoId(@PathParam("id") Integer id){
        Pago pago = this.service.listarPagoId(id);
        return Response.status(Response.Status.OK).entity(pago).build();
    }
    
    @GET
    @Path("/listarHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarHistorial(){
        List<Venta> lista = this.service.listarHistorial();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
}
