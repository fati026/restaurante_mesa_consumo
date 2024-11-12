/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ConsumoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MesaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Cliente;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.DetalleConsumo;

/**
 *
 * @author Verónica
 */
@Path("/consumos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsumoREST {
    
    @EJB
    private ConsumoDAO consumoDAO;
    private MesaDAO mesaDAO;
    
    @GET
    @Path("/mesa/{mesaId}")
    public Response obtenerConsumoActual(@PathParam("mesaId") int mesaId) {
        Consumo consumo = consumoDAO.obtenerConsumoActual(mesaId);
        if (consumo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mesa no ocupada").build();
        }
        return Response.ok(consumo).build();
    }
    
    @POST
    @Path("/mesa/{mesaId}/cliente/{clienteId}")
    public Response asignarCliente(@PathParam("mesaId") int mesaId, @PathParam("clienteId") Long clienteId) {
        Consumo consumo = consumoDAO.obtenerConsumoActual(mesaId);
        
        if (consumo == null) {
            consumo = new Consumo();
            consumo.setMesa(mesaDAO.findById(mesaId));
            consumo.setEstado("abierto");
            //consumo.setFechaCreacion(new LocalDateTime());
        }
        
        consumo.setCliente(new Cliente(clienteId)); // Asegúrate de que Cliente existe
        consumoDAO.guardarConsumo(consumo);
        
        return Response.ok(consumo).build();
    }

    @POST
    @Path("/mesa/{mesaId}/detalle")
    public Response agregarDetalle(@PathParam("mesaId") int mesaId, DetalleConsumo detalle) {
        Consumo consumo = consumoDAO.obtenerConsumoActual(mesaId);
        
        if (consumo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Mesa no ocupada").build();
        }
        
//        detalle.setConsumo(consumo);
//        consumo.getDetalles().add(detalle);
//        consumo.setTotal(consumo.getTotal() + calcularSubtotal(detalle));
        consumoDAO.guardarConsumo(consumo);
        
        return Response.ok(consumo).build();
    }

    private double calcularSubtotal(DetalleConsumo detalle) {
        // Implementa la lógica para calcular el subtotal (p.ej. precio de producto * cantidad)
        return 0.0; // Valor temporal
    }
}

