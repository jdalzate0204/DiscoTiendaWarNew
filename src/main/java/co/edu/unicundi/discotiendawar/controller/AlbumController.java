package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.AlbumDto;
import co.edu.unicundi.discotiendajar.entity.Album;
import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
import co.edu.unicundi.discotiendajar.exception.ResourceNotFoundException;
import co.edu.unicundi.discotiendajar.service.IAlbumService;
import co.edu.unicundi.discotiendajar.view.VistaAlbum;
import java.util.List;
import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author acer
 */
@Stateless
@Path("/albumes")
public class AlbumController {
    
    @EJB
    private IAlbumService service;
    
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardar(AlbumDto album) throws ResourceIllegalArgumentException, CloneNotSupportedException {
        this.service.guardar(album);
        return Response.status(Response.Status.CREATED).build();
    }
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAlbumes(){
        List<Album> lista = this.service.listar();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    
    @GET
    @Path("/listarAlbumes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarSelect() {
        List<Album> lista = this.service.listarSelect();
        return Response.status(Response.Status.OK).entity(lista).build();
    }
    @PUT
    @Path("/editar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(AlbumDto obj)throws ResourceIllegalArgumentException, CloneNotSupportedException {
       this.service.editar(obj);
       return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Path("/listarPorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarId(@PathParam("id")Integer id) throws ResourceNotFoundException {
        List<Album> album= this.service.listarPorId(id);
        return Response.status(Response.Status.OK).entity(album).build();
    }
    
    @GET
    @Path("/vista")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vista() {
        List<VistaAlbum> lista = this.service.vista();
        return Response.status(Response.Status.OK).entity(lista).build();
    }

}
