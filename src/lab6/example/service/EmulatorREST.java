package lab6.example.service;

import lab6.example.PATCH;
import lab6.rest.pojo.EntryPOJO;
import lab6.rest.pojo.SubstancePOJO;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
@Path("")
public class EmulatorREST {
    List<SubstancePOJO> substances = new ArrayList<SubstancePOJO>();

    boolean isEmulatorRunning = false;



    private static final String REST_URI = "http://localhost:8080/lab6_v2Web";
    private Client restClient;
    private WebTarget resourceTarget;



    public EmulatorREST() {
    }

    @POST
    @Path("/substances/") //update słownika substancji dla emulatora
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSubstances(List<SubstancePOJO> substances) {
//        setupSubstances();
        this.substances = substances;
        System.out.println(substances);
        for (SubstancePOJO substancePOJO : substances) {
            System.out.println(substancePOJO.getSubstanceName());
        }



        if(!isEmulatorRunning){
            startEmulator();
            isEmulatorRunning = true;
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }


//    private List<EntryPOJO> setupSubstances() {
//        restClient = ClientBuilder.newClient();
//        resourceTarget = restClient.target(REST_URI);
//        WebTarget methodTarget = resourceTarget.path("/substances/");
//        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
//        List<EntryPOJO> allEntry = invocationBuilder.get(new GenericType<List<EntryPOJO>>(){});
//        return allEntry;
//    }

    private void startEmulator() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sendAirQualities();
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 1 * 1000;
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }


    private void sendAirQualities() {
        System.out.println("sendAirQualities");


//                restClient = ClientBuilder.newClient();
//        resourceTarget = restClient.target(REST_URI);
//        WebTarget methodTarget = resourceTarget.path("/airquality/1");
//        Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
//        AirqualityPOJO airqualityPOJO = new AirqualityPOJO();
//        Substance substance = new Substance();
//        substance.setSubstanceName("substanceName");
//        airqualityPOJO.getSubstances().add(substance);
//      //  invocationBuilder.post(new GenericType<AirqualityREST>(){});
//      invocationBuilder.post(Entity.entity(airqualityPOJO, MediaType.APPLICATION_JSON));
//        //invocationBuilder.get(new GenericType<List<EntryPOJO>>(){});

    }

}
