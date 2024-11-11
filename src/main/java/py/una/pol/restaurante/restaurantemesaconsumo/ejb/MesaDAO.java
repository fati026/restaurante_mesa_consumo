package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import py.una.pol.restaurante.restaurantemesaconsumo.entities.Mesa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MesaDAO {

    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;

    // Crear una nueva mesa
    public void create(Mesa mesa) {
        em.persist(mesa);
    }

    // Buscar una mesa por su ID
    public Mesa findById(int id) {
        return em.find(Mesa.class, id);
    }

    // Obtener todas las mesas
    public List<Mesa> findAll() {
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m", Mesa.class);
        return query.getResultList();
    }

    // Actualizar una mesa existente
    public void update(Mesa mesa) {
        em.merge(mesa);
    }

    // Eliminar una mesa por ID
    public void delete(int id) {
        Mesa mesa = findById(id);
        if (mesa != null) {
            em.remove(mesa);
        }
    }
}
