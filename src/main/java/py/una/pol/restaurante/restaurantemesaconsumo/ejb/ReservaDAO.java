/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Mesa;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Reserva;

/**
 *
 * @author Verónica
 */
@Stateless
public class ReservaDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void guardarReserva(Reserva reserva) throws Exception {
        Mesa mesa = em.find(Mesa.class, reserva.getMesa());
        if (mesa == null || !mesa.getEstado().equals("libre")) {
            throw new Exception("La mesa no está disponible.");
        }
        if (!verificarDisponibilidad(reserva.getMesa(), reserva.getFechaHora())) {
            throw new Exception("La mesa ya está reservada para la fecha seleccionada.");
        }
        Reserva res = new Reserva();
        res.setMesa(reserva.getMesa());
        res.setFechaHora(reserva.getFechaHora());
        res.setCantidadPersonas(reserva.getCantidadPersonas());
        res.setContacto(reserva.getContacto());
        res.setCliente(reserva.getCliente());
        res.setEstado("pendiente");

        // Actualizar estado de la mesa a "reservada"
        mesa.setEstado("reservada");
        em.merge(mesa);
        
        em.persist(res);
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
    
    public void cancelarReserva(int id) throws Exception {
        Reserva reserva = em.find(Reserva.class, id);
        if (reserva == null || !reserva.getEstado().equals("pendiente")) {
            throw new Exception("La reserva no se puede cancelar.");
        }

        reserva.setEstado("cancelada");
        Mesa mesa = em.find(Mesa.class, reserva.getMesa());
        if (mesa != null) {
            mesa.setEstado("libre");
            em.merge(mesa);
        }

        em.merge(reserva);
    }
    
    public boolean verificarDisponibilidad(Mesa mesa, LocalDateTime fechaReserva) {
        Long count = em.createQuery(
            "SELECT COUNT(r) FROM Reserva r WHERE r.idMesa = :idMesa " +
            "AND r.estado IN ('pendiente', 'confirmada') " +
            "AND r.fechaReserva = :fechaReserva", Long.class)
            .setParameter("idMesa", mesa)
            .setParameter("fechaReserva", fechaReserva)
            .getSingleResult();

        return count == 0; 
    }
    
    public void confirmarReserva(int idReserva) throws Exception {
        Reserva reserva = em.find(Reserva.class, idReserva);
        if (reserva == null || !reserva.getEstado().equals("pendiente")) {
            throw new Exception("La reserva no se puede confirmar.");
        }

        reserva.setEstado("confirmada");
        Mesa mesa = em.find(Mesa.class, reserva.getMesa());
        if (mesa != null) {
            mesa.setEstado("ocupada");
            em.merge(mesa);
        }

        em.merge(reserva);
    }

}
