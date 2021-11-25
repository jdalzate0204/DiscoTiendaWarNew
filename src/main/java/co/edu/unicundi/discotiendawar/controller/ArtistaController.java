package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.ArtistaDto;
import co.edu.unicundi.discotiendajar.entity.*;
import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
import co.edu.unicundi.discotiendajar.exception.ResourceNotFoundException;
import co.edu.unicundi.discotiendajar.service.IArtistaService;
import co.edu.unicundi.discotiendajar.view.VistaArtista;
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
    
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(ArtistaDto obj)throws ResourceIllegalArgumentException, CloneNotSupportedException {
       this.service.editar(obj);
       return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Path("/listarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarId(@PathParam("id")Integer id) throws ResourceNotFoundException {
        List<Artista> artista= this.service.listarPorId(id);
        return Response.status(Response.Status.OK).entity(artista).build();
    }    

    @GET
    @Path("/listarVista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vista() {
        List<VistaArtista> lista = this.service.vista();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
}
