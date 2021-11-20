
package org.netbeans.rest.application.config;

import co.edu.unicundi.discotiendajar.exception.NoAutorizationException;
import co.edu.unicundi.discotiendajar.service.IAdministradorService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author ALEJANDRA
 */
@Provider
@PreMatching
public class Interceptor implements ContainerRequestFilter{
    @EJB
    private IAdministradorService service;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Entro al filtro");
        String url =  requestContext.getUriInfo().getAbsolutePath().toString();
        System.out.println(url);
        
        if(url.contains("/auth/login")||(url.contains("/albumes/listar"))) {
            return;
        }

        //Desde aqui si necesitan
         String token = requestContext.getHeaderString("Authorization");
         System.out.println(token);

         if(token == null) {
           requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
            .entity("TOKEN INVALIDO").build());
            return;
         } else {

             String key = "!E1BuRy$EHyw";


             try{
             //De claims convertir el json en un objeto java Y de ahi extraer el rol
             Claims claims =    Jwts.parser()
                                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                                .parseClaimsJws(token).getBody();

             System.out.println(claims.toString());
             System.out.println(claims.getExpiration().toString());
             System.out.println(claims.getSubject());

             //ir a la bd y valiar que el token este en la table token, si si sigue el proceso y si no entonces 
            int token2= this.service.validarToken(token);
            if(token2==1){
                
            
             //abortamos la peticion


            if(url.contains("/albumes/") && claims.toString().contains("administrador")) {
                return;
            } else if(url.contains("/artistas/") && claims.toString().contains("administrador")) {
                return;
            } else if(url.contains("/canciones/") && claims.toString().contains("administrador")) {
                return;
            } else {
                  //Enviar el objeto de error con codigo 401 y el dto  ExcepionWrraper
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("TOKEN NO PERMITIDO PARA ESTA OPERACION").build());
                return;   
            }

            }
             } catch(ExpiredJwtException ex) {
                  //Enviar el objeto de error con codigo 401 y el dto  ExcepionWrraper
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("TOKEN CADUCADO").build());
                return;
             } catch(Exception ex ){
                  //Enviar el objeto de error con codigo 500 y el dto  ExcepionWrraper
                             requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("ERROR AL DESCIFRAR EL TOKEN").build());
                return;
             }
            

         }



    }

   

}
