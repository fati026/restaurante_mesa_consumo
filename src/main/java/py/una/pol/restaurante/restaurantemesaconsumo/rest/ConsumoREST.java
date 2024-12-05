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
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ClienteDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ConsumoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ConsumoPagosDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ConsumoStockDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MesaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MetodoPagoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ProductoStockDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Cliente;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoPagos;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoStock;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.DetalleConsumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.MetodoPago;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Producto;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ProductoStock;

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
    @EJB
    private ProductoStockDAO productoStockDAO;  // Inyectando el DAO de ProductoStock
    
    @EJB
    private ConsumoStockDAO consumoStockDAO;    // Inyectando el DAO de ConsumoStock
    
    private MesaDAO mesaDAO;
    private ConsumoPagosDAO consumoPagosDAO;
    
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
         // Lógica para actualizar el inventario y generar alertas
        
       Producto producto = detalle.getProducto();  // Obtienes el Producto
       ProductoStock productoStock = productoStockDAO.findById(producto.getIdProducto());  // Buscas el ProductoStock asociado al Producto
       if (productoStock != null) {
            // Actualización del stock
            int nuevoStock = productoStock.getStockActual() - detalle.getCantidad();
            if (nuevoStock < 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("No hay suficiente stock para este producto.")
                    .build();
            }
//        detalle.setConsumo(consumo);
//        consumo.getDetalles().add(detalle);
//        consumo.setTotal(consumo.getTotal() + calcularSubtotal(detalle));
        
         // Actualizamos el stock del producto
          productoStock.setStockActual(nuevoStock);
          productoStockDAO.update(productoStock);
          consumoDAO.guardarConsumo(consumo);
        
           // Generar alerta si el stock es bajo
            if (nuevoStock < productoStock.getStockMinimo()) {
                // Aquí se podría generar una alerta en el sistema, o almacenar una alerta en la base de datos
                System.out.println("Alerta: Stock bajo para el producto ID " + detalle.getProducto().getId());
            }
          return Response.ok(consumo).build();
           } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("Producto no encontrado en el inventario.")
                .build();
        }   
    }
    private double calcularSubtotal(DetalleConsumo detalle) {
        // Implementa la lógica para calcular el subtotal (p.ej. precio de producto * cantidad)
        return 0.0; // Valor temporal
    } 
    
    @POST
    @Path("/mesa/{mesaId}/metodopago")
    public Response guardarPago(ConsumoPagos consumoPagos) {
        try {
            consumoPagosDAO.guardarPago(consumoPagos);
            return Response.status(Response.Status.CREATED).entity("Pago guardado exitosamente").build();
        } catch (Exception e) {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity(e.getMessage())
                       .build();
        }    
        
    }
    
    @DELETE
    @Path("/mesa/{mesaId}/metodopago/{metodoId}")
    public Response eliminarPago(@PathParam("id") int id) {
        ConsumoPagos consumoPagos = consumoPagosDAO.findById(id);
        if (consumoPagos != null) {
            consumoPagosDAO.delete(id);
            return Response.ok("Pago eliminado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Pago no encontrado").build();
        }
    }
}

