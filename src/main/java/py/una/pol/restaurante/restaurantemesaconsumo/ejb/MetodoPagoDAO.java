/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.MetodoPago;

/**
 *
 * @author Verónica
 */
@Stateless
public class MetodoPagoDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void agregar (MetodoPago metodoPago){
        this.em.persist(metodoPago);
    }

    public List<MetodoPago> listar(){
        Query q=this.em.createQuery("select * from metodos_pago c");
        return (List<MetodoPago>) q.getResultList();
    }
    
    public void actualizar(MetodoPago metodoPago) {
        em.merge(metodoPago);
    }
    
    public void delete(Long id) {
        MetodoPago metodoPago = findById(id);
        if (metodoPago != null) {
            em.remove(metodoPago);
        }
    }
    
    public MetodoPago findById(Long id) {
        return em.find(MetodoPago.class, id);
    }
}
