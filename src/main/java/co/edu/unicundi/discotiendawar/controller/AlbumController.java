package co.edu.unicundi.discotiendawar.controller;

import co.edu.unicundi.discotiendajar.dto.AlbumDto;
import co.edu.unicundi.discotiendajar.entity.Album;
import co.edu.unicundi.discotiendajar.exception.ResourceIllegalArgumentException;
import co.edu.unicundi.discotiendajar.service.IAlbumService;
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
}
