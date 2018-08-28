/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import io.swagger.jaxrs.config.BeanConfig;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Petar
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);

    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(controllers.KatedraController.class);
        resources.add(controllers.KorisnikController.class);
        resources.add(controllers.NastavnikController.class);
        resources.add(controllers.OsobaUVeziSaUdzbenikomController.class);
        resources.add(controllers.PredmetController.class);
        resources.add(controllers.PredmetNaStdProgramuController.class);
        resources.add(controllers.StatusPredmetaController.class);
        resources.add(controllers.StudijskiProgramController.class);
        resources.add(controllers.TematskaCelinaController.class);
        resources.add(controllers.TipNastaveController.class);
        resources.add(controllers.UdzbenikController.class);
        resources.add(controllers.UlogaController.class);
        resources.add(controllers.UlogaUdzbenikController.class);
        resources.add(controllers.VrstaINivoStudijaController.class);
    }

}
