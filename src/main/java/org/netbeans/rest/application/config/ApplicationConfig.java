package org.netbeans.rest.application.config;

//import io.swagger.jaxrs.config.BeanConfig;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author PAULA GUZMAN
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    /*public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setTitle("API swagger");
        beanConfig.setBasePath("/DiscoTiendaWar/api");
        beanConfig.setResourcePackage("co.edu.unicundi.discotiendawar.controller");
        beanConfig.setScan(true);
    }*/

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.unicundi.discotiendawar.controller.AdministradorController.class);
        resources.add(co.edu.unicundi.discotiendawar.controller.AlbumController.class);
        resources.add(co.edu.unicundi.discotiendawar.controller.ArtistaController.class);
        resources.add(co.edu.unicundi.discotiendawar.controller.CancionController.class);
        resources.add(co.edu.unicundi.discotiendawar.controller.VentaController.class);
        resources.add(co.edu.unicundi.discotiendawar.exception.ExceptionHandler.class);
        resources.add(org.netbeans.rest.application.config.Interceptor.class);
    }
    
}
