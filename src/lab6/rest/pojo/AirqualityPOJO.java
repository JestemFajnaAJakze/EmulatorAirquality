package lab6.rest.pojo;

import java.util.ArrayList;
import java.util.List;

public class AirqualityPOJO {

    private List<Substance> substances;

    public AirqualityPOJO() {
        substances = new ArrayList<>();
    }

    public List<Substance> getSubstances() {
        return substances;
    }

    public void setSubstances(List<Substance> substances) {
        this.substances = substances;
    }



}