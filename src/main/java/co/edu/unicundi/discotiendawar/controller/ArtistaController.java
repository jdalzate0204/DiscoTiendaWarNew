package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.ArtistaDto;
import co.edu.unicundi.discotiendajar.entity.*;
import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
import co.edu.unicundi.discotiendajar.service.IArtistaService;
import java.util.List;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author acer
 */
@Stateless
@Path("/artistas")
public class ArtistaController {
    
    @EJB
    private IArtistaService service;
    
    @GET
    @Path("/listarSexo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerSexo() {
        List<Sexo> lista = service.obtenerSexo();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @GET
    @Path("/listarGenero")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerGenero() {
        List<GeneroMusical> lista = service.obtenerGenero();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(ArtistaDto obj)throws ResourceIllegalArgumentException, CloneNotSupportedException {
       this.service.guardar(obj);
       return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/listarArtistas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerArtistas() {
        List<Artista> lista = this.service.listarSelect();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
     @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerLista() {
        List<Artista> lista = this.service.listar();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
}
