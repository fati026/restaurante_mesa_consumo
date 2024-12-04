package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import py.una.pol.restaurante.restaurantemesaconsumo.entities.Mesa;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Cliente;

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

    // Obtener mesas filtradas por estado (por ejemplo, "libre" o "ocupada")
    public List<Mesa> findByEstado(String estado) {
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m WHERE m.estado = :estado", Mesa.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    // Obtener mesas por área
    public List<Mesa> findByArea(String area) {
        TypedQuery<Mesa> query = em.createQuery("SELECT m FROM Mesa m WHERE m.area = :area", Mesa.class);
        query.setParameter("area", area);
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

    // Asignar un cliente a una mesa
    public void asignarCliente(int idMesa, Cliente cliente) {
        Mesa mesa = findById(idMesa);
        if (mesa != null) {
            mesa.setCliente(cliente);
            mesa.setEstado("ocupada"); // Cambiar estado a "ocupada"
            update(mesa);
        }
    }

    // Administrar la disposición de mesas: Definir área, capacidad y estado
    public void administrarMesa(int idMesa, String area, int capacidad, String estado) {
        Mesa mesa = findById(idMesa);
        if (mesa != null) {
            mesa.setArea(area);
            mesa.setCapacidad(capacidad);
            mesa.setEstado(estado); // Establecer estado de la mesa
            update(mesa);
        }
    }
}
