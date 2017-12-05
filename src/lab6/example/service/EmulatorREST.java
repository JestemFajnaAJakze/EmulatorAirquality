package lab6.example.service;

import lab6.rest.pojo.*;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Singleton
@Path("")
public class EmulatorREST {
    List<LiterarySubstancePOJO> literarySubstances = new ArrayList<LiterarySubstancePOJO>();
    List<StationPOJO> stations = new ArrayList<>();

    boolean isEmulatorRunning = false;



    private static final String REST_URI = "http://localhost:8080/lab6_v2Web";
    private static final String REST_URI_STATIONS = "http://localhost:8282/lab6_v2Web";
    private Client restClient;
    private WebTarget resourceTarget;



    public EmulatorREST() {

    }

    @POST
    @Path("/substances/") //update słownika substancji dla emulatora
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSubstances(List<LiterarySubstancePOJO> literarySubstances) {
//        setupSubstances();
        this.literarySubstances = literarySubstances;
        System.out.println(literarySubstances);
        for (LiterarySubstancePOJO literarySubstancePOJO : literarySubstances) {
            System.out.println(literarySubstancePOJO.getSubstanceName());
        }

        if(!isEmulatorRunning){
            addMockStations();
            startEmulator();
            isEmulatorRunning = true;
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private void addMockStations() {
        StationPOJO stationPOJO1 = new StationPOJO("1", new Address("Warszawa", "Kaliskiego"), new ArrayList<>());
        stations.add(stationPOJO1);

        StationPOJO stationPOJO2 = new StationPOJO("2", new Address("Krakow", "Grodzka"), new ArrayList<>());
        stations.add(stationPOJO2);

        StationPOJO stationPOJO3 = new StationPOJO("3", new Address("Gdansk", "Kochanowskiego"), new ArrayList<>());
        stations.add(stationPOJO3);

        StationPOJO stationPOJO4 = new StationPOJO("4", new Address("Szczecin", "Krakowska"), new ArrayList<>());
        stations.add(stationPOJO4);

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


    private void setupStationsSubstances() {
        for (StationPOJO station : stations) {
            List<SubstancePOJO> substances = new ArrayList<>();
            for (LiterarySubstancePOJO literarySubstance : literarySubstances) {
                double randomNum = 10 + (int)(Math.random() * 20);
                substances.add(new SubstancePOJO(literarySubstance.getSubstanceId(),literarySubstance.getSubstanceName(),randomNum));
            }
            station.setSubstances(substances);
        }
    }

    private void sendAirQualities() {
        System.out.println("sendAirQualities");
        for (StationPOJO station : stations) {
            setupStationsSubstances();

            restClient = ClientBuilder.newClient();
            resourceTarget = restClient.target(REST_URI_STATIONS);
            WebTarget methodTarget = resourceTarget.path("/airquality/" + station.getStationId());
            Invocation.Builder invocationBuilder = methodTarget.request(MediaType.APPLICATION_JSON);
            invocationBuilder.post(Entity.entity(station, MediaType.APPLICATION_JSON));
        }
    }
}
