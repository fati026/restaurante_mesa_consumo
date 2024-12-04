/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.rest;

/**
 *
 * @author Verónica
 */
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ClienteDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ReservaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MesaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Reserva;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaREST {
    
    @EJB
    private ReservaDAO reservaDAO;
    private MesaDAO mesaDAO;
    private ClienteDAO clienteDAO;
    
     // Crear un nuevo producto
    @POST
    public Response insertarReserva(Reserva reserva) {
        try {
            reservaDAO.guardarReserva(reserva);
            return Response.status(Response.Status.CREATED).entity("Reserva guardada exitosamente").build();
        } catch (Exception e) {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(e.getMessage())
                       .build();
        }    
    }
    @PUT
    @Path("/{id}")
    public Response actualizarReserva(@PathParam("id") int id, Reserva reserva) {
        Reserva reservaExistente = reservaDAO.findById(id);
        if (reservaExistente != null) {
            reserva.setId(id); 
            reservaDAO.actualizarReserva(reserva);
            return Response.ok("Reserva actualizada exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reserva no encontrada").build();
        }
    }
    
    @PUT
    @Path("/reservas/{id}/cancelar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelarReserva(@PathParam("id") int id) {
        try {
            reservaDAO.cancelarReserva(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }
    
    @PUT
    @Path("/reservas/{id}/confirmar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmarReserva(@PathParam("id") int id) {
        try {
            reservaDAO.confirmarReserva(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }
    
}
