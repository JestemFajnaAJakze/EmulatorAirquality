package lab6.rest.pojo;

public class SubstancePOJO {
    private String type;
    private String substanceName;
    private double value;

    public SubstancePOJO() {
    }

    public SubstancePOJO(String type, String substanceName, double value) {
        this.type = type;
        this.substanceName = substanceName;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
