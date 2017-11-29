package br.com.senior.business.facade;

import br.com.senior.business.dao.CityDao;
import br.com.senior.business.model.City;
import br.com.senior.business.model.aux.CitiesDistance;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CityFacade {

    @Inject
    private CityDao cityDao;

    /**
     *
     * @return - retorna uma lista com todos os objetos City ordenado pelo nome
     * da cidade
     */
    public List<City> findAll() {

        return cityDao.findAll();
    }

    /**
     *
     * @return - retorna uma lista com os objetos City que são capital
     */
    public List<City> findOnlyCapital() {

        return cityDao.findOnlyCapital();
    }

    /**
     *
     * @param ibgeId - entrar com o ibgeId da cidade
     * @return - retorna o objeto City encontrado
     */
    public City findByIbgeId(BigDecimal ibgeId) {

        return cityDao.findByIbgeId(ibgeId);
    }

    /**
     *
     * @param uf - entrar com a UF (unidade federal) do estado
     * @return - retorna uma lista com os objetos City encontrados
     */
    public List<City> findByUf(String uf) {

        return cityDao.findByUf(uf);
    }

    /**
     *
     * @param city - entrar com o objeto City a ser inserido
     * @return - retorna o objeto City inserido
     */
    public City insert(City city) {

        return cityDao.insert(city);
    }

    /**
     *
     * @param city - entrar com o objeto City a ser deletado
     */
    public void delete(City city) {

        cityDao.delete(city);
    }

    /**
     *
     * @param column - entrar com uma coluna que exista na tabela/csv
     * @param value - entrar com o valor a filtrar na coluna indicada
     * @return - retorna uma lista com os objetos City encontrados
     */
    public List<City> findByColumnAndValue(String column, String value) {

        try {

            List<City> cityList = findAll();
            List<City> cityListTmp = new ArrayList<>();

            for (City city : cityList) {

                //verifica qual a coluna foi indicada e dentro de cada case 
                //confere se o valor procurado bate como o valor vindo da tabela
                //caso seja adiciona na lista cityListTmp pois confere com os 
                //dois argumentos passados
                switch (column) {
                    case "ibgeId":
                        BigDecimal ibgeId = new BigDecimal(value);
                        if (city.getIbgeId() != null && city.getIbgeId().equals(ibgeId)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "uf":
                        if (city.getUf() != null && city.getUf().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "name":
                        if (city.getName() != null && city.getName().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "capital":
                        if (city.getCapital() != null && city.getCapital().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "lon":
                        BigDecimal lon = new BigDecimal(value);
                        if (city.getLon() != null && city.getLon().equals(lon)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "lat":
                        BigDecimal lat = new BigDecimal(value);
                        if (city.getLon() != null && city.getLon().equals(lat)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "noAccents":
                        if (city.getNoAccents() != null && city.getNoAccents().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "alternativeNames":
                        if (city.getAlternativeNames() != null && city.getAlternativeNames().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "microregion":
                        if (city.getMicroregion() != null && city.getMicroregion().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                    case "mesoregion":
                        if (city.getMesoregion() != null && city.getMesoregion().equals(value)) {

                            cityListTmp.add(city);
                        }
                        break;
                }
            }

            //retona a lista somente dos objetos City que conferem coluna e valor
            return cityListTmp;

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @param column - entrar com uma coluna existente no tabela
     * @return - retorna a quantidade de registros distintos
     */
    public Integer quantityRecordsByColumn(String column) {

        try {

            List<City> cityList = findAll();
            List<String> list = new ArrayList<>();

            //percorre a lista com todas as cidades
            for (City city : cityList) {

                String value;
                //verifica qual a coluna foi indicada e dentro de cada case 
                //confere se o valor já conta na list se não constar adiciona
                switch (column) {
                    case "ibgeId":
                        value = String.valueOf(city.getIbgeId());
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "uf":
                        value = city.getUf();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "name":
                        value = city.getName();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "capital":
                        value = city.getCapital();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "lon":
                        value = String.valueOf(city.getLon());
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "lat":
                        value = String.valueOf(city.getLat());
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "noAccents":
                        value = city.getNoAccents();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "alternativeNames":
                        value = city.getAlternativeNames();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "microregion":
                        value = city.getMicroregion();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                    case "mesoregion":
                        value = city.getMesoregion();
                        if (value != null && !list.contains(value)) {

                            list.add(value);
                        }
                        break;
                }
            }

            return list.size();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @return - retorna a quantidade total dos registros na tabela
     */
    public Integer totalNumberOfRecords() {

        List<City> cityList = findAll();
        return cityList.size();
    }

    /**
     *
     * @return - retorna o objeto CitiesDistance que contem dois objetos City
     * (mais distantes um do outro) e a distancia entre os dois em quilometros
     */
    public CitiesDistance longerDistanceBetweenTwoCities() {

        //obtem uma lista dos objetos City ordenados pela latitude e longitude
        //assim o primeiro e o ultimo objetos terão a localização mais distante
        List<City> cityList = cityDao.findAllOrderByLatLon();

        //seta city1 o primeito objeto da lista
        City city1 = cityList.get(0);

        //seta city2 o ultimo objeto da lista
        City city2 = cityList.get(cityList.size() - 1);

        //obtem a distancia entre as duas cidades referente a sua localização
        double distanceInKilometers = getDistanceInKilometers(
                city1.getLat(),
                city1.getLon(),
                city2.getLat(),
                city2.getLon());

        //seta os dois objetos City e a distancia entre eles
        CitiesDistance citiesDistance = new CitiesDistance(city1, city2, distanceInKilometers);

        return citiesDistance;
    }

    /**
     *
     * @param lat1 - entrar com a latitude da primeira cidade
     * @param lon1 - entrar com a longitude da primeira cidade
     * @param lat2 - entrar com a latitude da segunda cidade
     * @param lon2 - entrar com a longitude da segunda cidade
     * @return - retorna a distancia em quilometros entre das duas localizações
     * indicadas
     */
    private static double getDistanceInKilometers(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {

        try {

            double earthRadius = 6371000;
            double dLat = Math.toRadians(lat2.doubleValue() - lat1.doubleValue());
            double dLng = Math.toRadians(lon2.doubleValue() - lon1.doubleValue());
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1.doubleValue())) * Math.cos(Math.toRadians(lat2.doubleValue()))
                    * Math.sin(dLng / 2) * Math.sin(dLng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dist = (double) (earthRadius * c);

            return (dist / 1000);

        } catch (Exception e) {

            System.err.println(e);
        }

        return 0.0;
    }

}
