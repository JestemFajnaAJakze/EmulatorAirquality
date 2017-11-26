package lab6;

import lab6.example.service.AirqualityREST;
import lab6.example.service.SubstanceREST;
import lab6.rest.pojo.AirqualityPOJO;
import lab6.rest.pojo.Substance;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationPath("/*")
public class RESTApplication extends ResourceConfig {

    private static final String REST_URI = "http://localhost:8080/lab6_v2Web";
    private Client restClient;
    private WebTarget resourceTarget;

    public RESTApplication() {

        packages("lab6");
        System.out.println("start metoda");
        emulateAirqualities();
        System.out.println("stop metoda");

        register(JacksonFeature.class);

    }
;

    private void emulateAirqualities(){
        restClient = ClientBuilder.newClient();
        resourceTarget = restClient.target(REST_URI);
        WebTarget methodTarget = resourceTarget.path("/airquality/1");
        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
        AirqualityPOJO airqualityPOJO = new AirqualityPOJO();
        Substance substance = new Substance();
        substance.setSubstanceName("substanceName");
        airqualityPOJO.getSubstances().add(substance);
      //  invocationBuilder.post(new GenericType<AirqualityREST>(){});
      invocationBuilder.post(Entity.entity(airqualityPOJO, MediaType.APPLICATION_JSON));
        //invocationBuilder.get(new GenericType<List<EntryPOJO>>(){});
    }
}
