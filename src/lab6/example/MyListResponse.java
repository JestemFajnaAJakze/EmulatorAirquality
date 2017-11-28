package lab6.example;

import lab6.rest.pojo.LiterarySubstancePOJO;

import java.util.List;

public class MyListResponse {
    List<LiterarySubstancePOJO> substances;

    public List<LiterarySubstancePOJO> getSubstances() {
        return substances;
    }

    public void setSubstances(List<LiterarySubstancePOJO> substances) {
        this.substances = substances;
    }
}
