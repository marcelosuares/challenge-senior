package br.com.senior.api.representation;

import java.math.BigDecimal;

/**
 *
 *  @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
public class CityRepresentation {

    private BigDecimal ibgeId;
    private String uf;
    private String name;
    private String capital;
    private BigDecimal lon;
    private BigDecimal lat;
    private String noAccents;
    private String alternativeNames;
    private String microregion;
    private String mesoregion;

    public CityRepresentation(BigDecimal ibgeId, String uf, String name,
            String capital, BigDecimal lon, BigDecimal lat, String noAccents,
            String alternativeNames, String microregion, String mesoregion) {

        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.noAccents = noAccents;
        this.alternativeNames = alternativeNames;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public BigDecimal getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(BigDecimal ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }
}
