package br.com.senior.business.model.aux;

import br.com.senior.business.model.City;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
public class CitiesDistance {

    private City city1;
    private City city2;
    private Double distanceInKilometers;

    public CitiesDistance() {
    }

    public CitiesDistance(City city1, City city2, Double distanceInKilometers) {
        this.city1 = city1;
        this.city2 = city2;
        this.distanceInKilometers = distanceInKilometers;
    }

    public City getCity1() {
        return city1;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public City getCity2() {
        return city2;
    }

    public void setCity2(City city2) {
        this.city2 = city2;
    }

    public Double getDistanceInKilometers() {
        return distanceInKilometers;
    }

    public void setDistanceInKilometers(Double distanceInKilometers) {
        this.distanceInKilometers = distanceInKilometers;
    }

    @Override
    public String toString() {
        return "CitiesDistance{" + "city1=" + city1 + ", city2=" + city2
                + ", distanceInKilometers=" + distanceInKilometers + '}';
    }
}
