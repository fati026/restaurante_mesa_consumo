/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Reserva;

/**
 *
 * @author Verónica
 */
@Stateless
public class ReservaDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void guardarReserva(Reserva reserva) {
        em.persist(reserva);
    }
    
    public void actualizarReserva(Reserva reserva) {
        em.merge(reserva);
    }
    
    public List<Reserva> listarReservas() {
        return em.createQuery("SELECT c FROM reservas c", Reserva.class).getResultList();
    }
    
    public Reserva findById(int id) {
        return em.find(Reserva.class, id);
    }

}
