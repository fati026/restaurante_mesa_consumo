package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoStock;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ProductoStock;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.DetalleConsumo;

@Stateless
public class ConsumoStockDAO {

    @PersistenceContext
    private EntityManager em;

    // Método para obtener un ConsumoStock por ID
    public ConsumoStock findById(Integer id) {
        return em.find(ConsumoStock.class, id);
    }

    // Método para guardar un ConsumoStock y actualizar el stock del Producto
    public void save(ConsumoStock consumoStock) {
        // Primero, persistir el ConsumoStock
        em.persist(consumoStock);

        // Después, actualizar el stock del Producto
        ProductoStock productoStock = consumoStock.getProductoStock();
        if (productoStock != null) {
            int cantidadConsumida = consumoStock.getCantidad();
            productoStock.setStockActual(productoStock.getStockActual() - cantidadConsumida);
            em.merge(productoStock); // Actualizamos el ProductoStock

            // Verificar si el stock está por debajo del nivel mínimo
            if (productoStock.getStockActual() < productoStock.getStockMinimo()) {
                // Generar alerta si el stock está por debajo del nivel mínimo
                System.out.println("¡ALERTA! El stock del producto " + productoStock.getIdProducto() + " está por debajo del nivel mínimo.");
            }
        }
    }

    // Método para eliminar un ConsumoStock por ID
    public void delete(Integer id) {
        ConsumoStock consumoStock = findById(id);
        if (consumoStock != null) {
            em.remove(consumoStock);
        }
    }
}
