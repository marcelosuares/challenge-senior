package br.com.senior.business.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
@Entity
@Table(name = "TB_CITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll",
            query = "SELECT c FROM City c"),

    @NamedQuery(name = "City.findOnlyCapital",
            query = "SELECT c FROM City c WHERE c.capital = 'true' ORDER BY c.name"),

    @NamedQuery(name = "City.findByIbgeId",
            query = "SELECT c FROM City c WHERE c.ibgeId = :ibgeId"),

    @NamedQuery(name = "City.findByUf",
            query = "SELECT c FROM City c WHERE c.uf = :uf ORDER BY c.name"),

    @NamedQuery(name = "City.findAllOrderByLat",
            query = "SELECT c FROM City c ORDER BY c.lat"),
    
    @NamedQuery(name = "City.findAllOrderByLon",
            query = "SELECT c FROM City c ORDER BY c.lon")
})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "cityIdGen", sequenceName = "sq_city", allocationSize = 1)
    @GeneratedValue(generator = "cityIdGen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "IBGE_ID")
    private BigDecimal ibgeId;

    @Column(name = "UF")
    private String uf;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CAPITAL")
    private String capital;

    @Column(name = "LON")
    private BigDecimal lon;

    @Column(name = "LAT")
    private BigDecimal lat;

    @Column(name = "NO_ACCENTS")
    private String noAccents;

    @Column(name = "ALTERNATIVE_NAMES")
    private String alternativeNames;

    @Column(name = "MICROREGION")
    private String microregion;

    @Column(name = "MESOREGION")
    private String mesoregion;

    public City() {
    }

    public City(Integer id) {
        this.id = id;
    }

    public City(BigDecimal ibgeId, String uf, String name,
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ibgeId != null ? ibgeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.ibgeId == null && other.ibgeId != null) || (this.ibgeId != null && !this.ibgeId.equals(other.ibgeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", ibgeId=" + ibgeId + ", uf=" + uf
                + ", name=" + name + ", capital=" + capital + ", lon=" + lon
                + ", lat=" + lat + ", noAccents=" + noAccents
                + ", alternativeNames=" + alternativeNames + ", microregion=" + microregion
                + ", mesoregion=" + mesoregion + '}';
    }
}
