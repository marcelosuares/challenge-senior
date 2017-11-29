package br.com.senior.business.model.aux;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
public class StateAndQuantityCities {

    private String stateName;
    private String type;
    private Integer quantityCities;

    public StateAndQuantityCities() {
    }

    public StateAndQuantityCities(String stateName, String type, Integer quantity) {
        this.stateName = stateName;
        this.type = type;
        this.quantityCities = quantity;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantityCities() {
        return quantityCities;
    }

    public void setQuantityCities(Integer quantityCities) {
        this.quantityCities = quantityCities;
    }
}
