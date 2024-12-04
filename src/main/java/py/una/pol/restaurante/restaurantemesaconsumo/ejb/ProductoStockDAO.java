package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import py.una.pol.restaurante.restaurantemesaconsumo.entities.Producto;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Categoria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ProductoStock;

@Stateless
public class ProductoStockDAO {

    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;

    // Crear un nuevo producto
    public void create(Producto producto) {
        em.persist(producto);
    }

    // Buscar un producto por su ID
    public ProductoStock findById(Long id) {
        return em.find(ProductoStock.class, id);
    }

    // Obtener todos los productos
    public List<Producto> findAll() {
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    // Actualizar un producto existente
    public void update(ProductoStock productoStock) {
        em.merge(productoStock);
    }

    // Eliminar un producto por ID
    public void delete(Long id) {
        ProductoStock productoStock = findById(id);
        if (productoStock != null) {
            em.remove(productoStock);
        }
    }
}

//