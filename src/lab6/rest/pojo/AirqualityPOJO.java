package lab6.rest.pojo;

import java.util.ArrayList;
import java.util.List;

public class AirqualityPOJO {

    private List<SubstancePOJO> substancePOJOS;

    public AirqualityPOJO() {
        substancePOJOS = new ArrayList<>();
    }

    public List<SubstancePOJO> getSubstancePOJOS() {
        return substancePOJOS;
    }

    public void setSubstancePOJOS(List<SubstancePOJO> substancePOJOS) {
        this.substancePOJOS = substancePOJOS;
    }



}