package lab6;

import lab6.example.service.EmulatorREST;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {

    public RESTApplication() {
        packages("lab6");
        register(EmulatorREST.class);
        register(JacksonFeature.class);
    }
}
