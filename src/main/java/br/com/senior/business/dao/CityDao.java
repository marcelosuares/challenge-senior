package br.com.senior.business.dao;

import br.com.senior.business.model.City;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marcelo S. Silva <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @return - retorna uma lista com todos os objetos City ordenado pelo nome
     * da cidade
     */
    public List<City> findAll() {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findAll", City.class);

            return typedQuery.getResultList();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @return - retorna uma lista com os objetos City que são capital
     */
    public List<City> findOnlyCapital() {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findOnlyCapital", City.class);

            return typedQuery.getResultList();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @param ibgeId - entrar com o ibgeId da cidade
     * @return - retorna o objeto City encontrado
     */
    public City findByIbgeId(BigDecimal ibgeId) {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findByIbgeId", City.class);
            typedQuery.setParameter("ibgeId", ibgeId);

            //verifca se retornou algum registro
            if (typedQuery.getResultList().size() == 1) {

                //retorna o primeiro registro
                return typedQuery.getResultList().get(0);

            }

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @param uf - entrar com a UF (unidade federal) do estado
     * @return - retorna uma lista com os objetos City encontrados
     */
    public List<City> findByUf(String uf) {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findByUf", City.class);
            typedQuery.setParameter("uf", uf);

            return typedQuery.getResultList();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @param city - entrar com o objeto City a ser inserido
     * @return - retorna o objeto City inserido
     */
    public City insert(City city) {

        try {

            entityManager.persist(city);
            return city;

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }

    /**
     *
     * @param city - entrar com o objeto City a ser deletado
     */
    public void delete(City city) {

        try {

            City c = entityManager.merge(city);
            entityManager.remove(c);

        } catch (Exception e) {

            System.err.println(e);
        }
    }

    /**
     *
     * @return - retorna uma lista com todos os objetos City ordenado pela latitude
     */
    public List<City> findAllOrderByLat() {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findAllOrderByLat", City.class);

            return typedQuery.getResultList();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }
    
    /**
     *
     * @return - retorna uma lista com todos os objetos City ordenado pela longitude
     */
    public List<City> findAllOrderByLon() {

        try {

            TypedQuery<City> typedQuery = entityManager
                    .createNamedQuery("City.findAllOrderByLon", City.class);

            return typedQuery.getResultList();

        } catch (Exception e) {

            System.err.println(e);
        }

        return null;
    }
}
