package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import py.una.pol.restaurante.restaurantemesaconsumo.entities.Categoria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CategoriaDAO {

    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;

    // Crear una nueva categoría
    public void create(Categoria categoria) {
        em.persist(categoria);
    }

    // Buscar una categoría por su ID
    public Categoria findById(Long id) {
        return em.find(Categoria.class, id);
    }

    // Obtener todas las categorías
    public List<Categoria> findAll() {
        TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
    }

    // Actualizar una categoría existente
    public void update(Categoria categoria) {
        em.merge(categoria);
    }

    // Eliminar una categoría
    public void delete(Long id) {
        Categoria categoria = findById(id);
        if (categoria != null) {
            em.remove(categoria);
        }
    }
}
