/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;

/**
 *
 * @author Verónica
 */
@Stateless
public class ConsumoDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void guardarConsumo(Consumo consumo) {
        em.persist(consumo);
    }
    
    public void actualizarConsumo(Consumo consumo) {
        em.merge(consumo);
    }
    
    public List<Consumo> listarConsumos() {
        return em.createQuery("SELECT c FROM consumo c", Consumo.class).getResultList();
    }
    
    public Consumo obtenerConsumoActual(int mesaId) {
        return em.createQuery("SELECT c FROM Consumo c WHERE c.mesaId = :mesaId AND c.estado = 'abierto'", Consumo.class)
                 .setParameter("mesaId", mesaId)
                 .getResultStream()
                 .findFirst()
                 .orElse(null);
    }
    
    public Consumo findById(int id) {
        return em.find(Consumo.class, id);
    }
}
