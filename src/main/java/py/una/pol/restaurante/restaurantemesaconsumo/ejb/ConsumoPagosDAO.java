/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoPagos;

/**
 *
 * @author Verónica
 */
@Stateless
public class ConsumoPagosDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void guardarPago(ConsumoPagos consumoPagos) throws Exception {
        Consumo consumo = em.find(Consumo.class, consumoPagos.getConsumo());
        if (consumo == null || !consumo.getEstado().equals("ocupado")) {
            throw new Exception("El consumo no está disponible.");
        }
        ConsumoPagos cons = new ConsumoPagos();
        cons.setConsumo(consumoPagos.getConsumo());
        cons.setCliente(consumoPagos.getCliente());
        cons.setMetodoPago(consumoPagos.getMetodoPago());
        cons.setMonto(consumoPagos.getMonto());
        cons.setFechaHora(consumoPagos.getFechaHora());
        
        // Actualizar estado de la mesa a "reservada"
        consumo.setEstado("libre");
        em.merge(consumo);
        
        em.persist(cons);
    }
    
    public void delete(Integer id) {
        ConsumoPagos consumoPagos = findById(id);
        if (consumoPagos != null) {
            em.remove(consumoPagos);
        }
    }
    
    public ConsumoPagos findById(int id) {
        return em.find(ConsumoPagos.class, id);
    }
}
