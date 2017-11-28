package lab6;

import lab6.example.service.EmulatorREST;
import lab6.rest.pojo.AirqualityPOJO;
import lab6.rest.pojo.Substance;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.Timer;
import java.util.TimerTask;

@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {

    public RESTApplication() {
        packages("lab6");
        register(EmulatorREST.class);
        register(JacksonFeature.class);
    }
}
