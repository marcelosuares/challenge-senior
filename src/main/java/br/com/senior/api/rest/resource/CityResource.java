package br.com.senior.api.rest.resource;

import br.com.senior.business.facade.CityFacade;
import br.com.senior.business.model.City;
import br.com.senior.api.representation.CityRepresentation;
import br.com.senior.business.model.aux.StateAndQuantityCities;
import br.com.senior.business.model.aux.CitiesDistance;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
@Path("city")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CityResource {

    @EJB
    private CityFacade cityFacade;

    /**
     *
     * @param cityList - entra com a lista do objeto City
     * @return - retorna uma lista do objeto CityRepresentation
     */
    private List<CityRepresentation> processCityRepresentationList(List<City> cityList) {

        try {

            List<CityRepresentation> cityRepresentationList = new ArrayList<>();

            for (City city : cityList) {

                CityRepresentation cityRepresentation
                        = new CityRepresentation(
                                city.getIbgeId(),
                                city.getUf(),
                                city.getName(),
                                city.getCapital(),
                                city.getLon(),
                                city.getLat(),
                                city.getNoAccents(),
                                city.getAlternativeNames(),
                                city.getMicroregion(),
                                city.getMesoregion());

                cityRepresentationList.add(cityRepresentation);
            }

            return cityRepresentationList;

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @return - retorna um objeto JSON com os dados das cidades que são
     * capitais
     *
     * Exemplo: http://localhost:8080/challenge-senior/api/city/find/onlyCapital
     *
     * [ {
     * "ibgeId" : 2800308, "uf" : "SE", "name" : "Aracaju", "capital" : "true",
     * "lon" : -37.048213, "lat" : -10.907216, "noAccents" : "Aracaju",
     * "microregion" : "Aracaju", "mesoregion" : "Leste Sergipano" }, { "ibgeId"
     * : 1501402, "uf" : "PA", "name" : "Belm", "capital" : "true", "lon" :
     * -48.487826, "lat" : -1.459845, "noAccents" : "Belem", "microregion" :
     * "Belm", "mesoregion" : "Metropolitana de Belm" }]
     */
    @GET
    @Path("find/onlyCapital")
    public Response findOnlyCapital() {

        try {

            List<City> cityList = cityFacade.findOnlyCapital();
            List<CityRepresentation> cityRepresentationList = processCityRepresentationList(cityList);

            if (cityRepresentationList.isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cityRepresentationList)).build();
            }

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @return - retorna um objeto JSON com o estado que contem mais cidades e o
     * que contem menos
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/find/statesHighestAndSmallestQuantityCities
     *
     * [ {
     * "stateName" : "DF", "type" : "smallestQuantityCities", "quantityCities" :
     * 1 }, { "stateName" : "MG", "type" : "highestQuantityCities",
     * "quantityCities" : 853 } ]
     */
    @GET
    @Path("find/statesHighestAndSmallestQuantityCities")
    public Response findStatesHighestAndSmallestQuantityCities() {

        try {
            List<StateAndQuantityCities> stateAndQuantityCitiesList = cityFacade.getQuantityCitiesByState();

            List<StateAndQuantityCities> stateAndQuantityListReturn = new ArrayList<>();

            //obtem o primeiro objeto da lista que contem menos cidades
            StateAndQuantityCities stateAndQuantity = stateAndQuantityCitiesList.get(0);
            stateAndQuantity.setType("smallestQuantityCities");
            stateAndQuantityListReturn.add(stateAndQuantity);

            //obtem o ultimo objeto da lista que contem menos cidades
            stateAndQuantity = stateAndQuantityCitiesList.get(stateAndQuantityCitiesList.size() - 1);
            stateAndQuantity.setType("highestQuantityCities");
            stateAndQuantityListReturn.add(stateAndQuantity);

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(stateAndQuantityListReturn)).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @return - retorna um JSON com a quantidade de cidades por estado (UF)
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/find/quantityCitiesByState
     *
     * [ {
     * "stateName" : "DF", "quantityCities" : 1 }, { "stateName" : "RR",
     * "quantityCities" : 15 }]
     */
    @GET
    @Path("find/quantityCitiesByState")
    public Response findQuantityCitiesByState() {

        try {

            List<StateAndQuantityCities> stateAndQuantityCitiesList = cityFacade.getQuantityCitiesByState();

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(stateAndQuantityCitiesList)).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param ibgeId - entrar com ibgeId
     * @return - retorna um objeto JSON com os dados da cidade
     *
     * Exemplo:
     *
     * {
     * "ibgeId" : 1502103, "uf" : "PA", "name" : "Camet", "lon" : -49.49783,
     * "lat" : -2.246838, "noAccents" : "Cameta", "microregion" : "Camet",
     * "mesoregion" : "Nordeste Paraense" }
     */
    @GET
    @Path("find/ibgeId/{ibgeId}")
    public Response findByIbgeId(@PathParam("ibgeId") BigDecimal ibgeId) {

        try {

            City city = cityFacade.findByIbgeId(ibgeId);

            if (city == null) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                CityRepresentation cityRepresentation
                        = new CityRepresentation(
                                city.getIbgeId(),
                                city.getUf(),
                                city.getName(),
                                city.getCapital(),
                                city.getLon(),
                                city.getLat(),
                                city.getNoAccents(),
                                city.getAlternativeNames(),
                                city.getMicroregion(),
                                city.getMesoregion());

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cityRepresentation)).build();
            }

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param uf - entrar com o uf do estado
     * @return - retorna um objeto JSON com a lista de cidades por estado (UF)
     *
     * Exemplo: http://localhost:8080/challenge-senior/api/city/find/uf/RR
     *
     * [ {
     * "ibgeId" : 1400050, "uf" : "RR", "name" : "Alto Alegre", "lon" :
     * -61.314022, "lat" : 2.993823, "noAccents" : "Alto Alegre", "microregion"
     * : "Boa Vista", "mesoregion" : "Norte de Roraima" }, { "ibgeId" : 1400027,
     * "uf" : "RR", "name" : "Amajari", "lon" : -61.42059, "lat" : 3.652118,
     * "noAccents" : "Amajari", "microregion" : "Boa Vista", "mesoregion" :
     * "Norte de Roraima" }]
     */
    @GET
    @Path("find/uf/{uf}")
    public Response findByUf(@PathParam("uf") String uf) {

        try {

            List<City> cityList = cityFacade.findByUf(uf);
            List<CityRepresentation> cityRepresentationList = processCityRepresentationList(cityList);

            if (cityRepresentationList.isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cityRepresentationList)).build();
            }

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param ibgeId - entrar com o ibgeId a ser deletado
     * @return - retorna 200 OK se deletado com sucesso
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/delete/ibgeId/1502103
     */
    @DELETE
    @Path("/delete/ibgeId/{ibgeId}")
    @Produces(MediaType.APPLICATION_XML)
    public Response remove(@PathParam("ibgeId") BigDecimal ibgeId) {

        try {

            City city = cityFacade.findByIbgeId(ibgeId);
            cityFacade.delete(city);

            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param ibgeId - entrar com o ibgeId
     * @param uf - entrar com o unidade federal do estado
     * @param name - entrar com o nome da cidade
     * @param capital - entrar com 'true' caso a cidade seja capital
     * @param lon - entrar com a longitude ex: -61.314022
     * @param lat - entrar com a latitude ex: 2.993823
     * @param noAccents - entrar com o nome da cidade sem acentos
     * @param alternativeNames - entrar com os nomes alternativos da cidade
     * @param microregion - entrar com a microrregião onde se localiza cidade
     * @param mesoregion - entrar com a mesorregião onde se localiza a cidade
     * @return - retorna 200 OK se inserido com sucesso
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/insert/ibgeId/1234575/uf/SP/name/Ibaté/capital/%20/lon/-49.49747/lat/-2.246847/noAccents/Ibate/alternativeNames/%20/microregion/São%20Carlos/mesoregion/Ribeirão%20Preto
     *
     */
    @PUT
    @Path("/insert/ibgeId/{ibgeId}/uf/{uf}/name/{name}/capital/{capital}"
            + "/lon/{lon}/lat/{lat}/noAccents/{noAccents}/alternativeNames/{alternativeNames}"
            + "/microregion/{microregion}/mesoregion/{mesoregion}")
    public Response insert(
            @PathParam("ibgeId") BigDecimal ibgeId,
            @PathParam("uf") String uf,
            @PathParam("name") String name,
            @PathParam("capital") String capital,
            @PathParam("lon") BigDecimal lon,
            @PathParam("lat") BigDecimal lat,
            @PathParam("noAccents") String noAccents,
            @PathParam("alternativeNames") String alternativeNames,
            @PathParam("microregion") String microregion,
            @PathParam("mesoregion") String mesoregion
    ) {
        try {

            City city = new City(ibgeId, uf, name, capital, lon, lat, noAccents,
                    alternativeNames, microregion, mesoregion);

            cityFacade.insert(city);

            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

    }

    /**
     *
     * @param column - entrar com a coluna desejada
     * @param value - entrar com o valor referente a coluna a ser filtrado
     * @return - retorna um objeto JSON com os dados dados das cidades referente
     * ao filtro
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/find/column/name/value/Bonito
     *
     * [ {
     * "ibgeId" : 1501600, "uf" : "PA", "name" : "Bonito", "lon" : -47.305892,
     * "lat" : -1.367474, "noAccents" : "Bonito", "microregion" : "Bragantina",
     * "mesoregion" : "Nordeste Paraense" }, { "ibgeId" : 2602308, "uf" : "PE",
     * "name" : "Bonito", "lon" : -35.728528, "lat" : -8.472261, "noAccents" :
     * "Bonito", "microregion" : "Brejo Pernambucano", "mesoregion" : "Agreste
     * Pernambucano" }, { "ibgeId" : 2904050, "uf" : "BA", "name" : "Bonito",
     * "lon" : -41.268403, "lat" : -11.969573, "noAccents" : "Bonito",
     * "microregion" : "Seabra", "mesoregion" : "Centro Sul Baiano" }, {
     * "ibgeId" : 5002209, "uf" : "MS", "name" : "Bonito", "lon" : -56.492859,
     * "lat" : -21.124341, "noAccents" : "Bonito", "microregion" : "Bodoquena",
     * "mesoregion" : "Sudoeste de Mato Grosso do Sul" } ]
     */
    @GET
    @Path("find/column/{column}/value/{value}")
    public Response findByColumnAndValue(
            @PathParam("column") String column,
            @PathParam("value") String value) {

        try {

            List<City> cityList = cityFacade.findByColumnAndValue(column, value);

            if (cityList == null || cityList.isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                List<CityRepresentation> cityRepresentationList = processCityRepresentationList(cityList);

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cityRepresentationList)).build();
            }

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param column - entrar com a coluna desejada
     * @return - retorna um objeto JSON com a quantidade de registros distintos
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/find/quantityRecords/column/name
     *
     * 5264
     *
     */
    @GET
    @Path("find/quantityRecords/column/{column}")
    public Response quantityRecordsByColumn(
            @PathParam("column") String column) {

        try {

            Integer quantityRecords = cityFacade.quantityRecordsByColumn(column);

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(quantityRecords)).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @return - retorna um objeto JSON com a quantidade total de registros
     *
     * Exemplo:
     * http://localhost:8080/challenge-senior/api/city/get/totalNumberOfRecords
     *
     * 5566
     */
    @GET
    @Path("get/totalNumberOfRecords")
    public Response totalNumberOfRecords() {

        try {

            Integer totalNumberOfRecords = cityFacade.totalNumberOfRecords();

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(totalNumberOfRecords)).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @return - retorna um objeto JSON com as duas cidades mais distantes uma
     * da outra
     *
     * Exemplo: http://localhost:8080/challenge-senior/api/city/find/longerDistanceBetweenTwoCities 
     *
     * {
     * "city1" : { "id" : 62, "ibgeId" : 1200336, "uf" : "AC", "name" : "Mncio
     * Lima", "lon" : -72.916501, "lat" : -7.593223, "noAccents" : "Mancio
     * Lima", "microregion" : "Cruzeiro do Sul", "mesoregion" : "Vale do Juru"
     * }, "city2" : { "id" : 1525, "ibgeId" : 2605459, "uf" : "PE", "name" :
     * "Fernando de Noronha", "lon" : -32.435186, "lat" : -3.852021, "noAccents"
     * : "Fernando de Noronha", "microregion" : "Fernando de Noronha",
     * "mesoregion" : "Metropolitana de Recife" }, "distanceInKilometers" :
     * 4496.367501871953 }
     */
    @GET
    @Path("find/longerDistanceBetweenTwoCities")
    public Response longerDistanceBetweenTwoCities() {

        try {

            CitiesDistance citiesDistance = cityFacade.longerDistanceBetweenTwoCities();
            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(citiesDistance)).build();

        } catch (Exception e) {

            System.err.println(e);
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }
}
