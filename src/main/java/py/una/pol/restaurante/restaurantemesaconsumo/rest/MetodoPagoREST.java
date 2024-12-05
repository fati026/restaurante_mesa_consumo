/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MetodoPagoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.MetodoPago;

/**
 *
 * @author Verónica
 */
@Path("/metodospago")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MetodoPagoREST {
    @Inject
    private MetodoPagoDAO metodoPagoDAO;
    
    @GET
    public Response obtenerMetodosPago() {
        List<MetodoPago> metodospago = metodoPagoDAO.listar();
        return Response.ok(metodospago).build();
    }
    
    @GET
    @Path("/{id}")
    public Response obtenerMetodoPagoPorId(@PathParam("id") Long id) {
        MetodoPago metodoPago = metodoPagoDAO.findById(id);
        if (metodoPago != null) {
            return Response.ok(metodoPago).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Método de pago no encontrado").build();
        }
    }
    
    @POST
    public Response insertarMetodoPago(MetodoPago metodoPago) {
        metodoPagoDAO.agregar(metodoPago);
        return Response.status(Response.Status.CREATED).entity("Método de pago creado exitosamente").build();
    }
    
    @PUT
    @Path("/{id}")
    public Response actualizarMetodoPago(@PathParam("id") Long id, MetodoPago metodoPago) {
        MetodoPago metodoExistente = metodoPagoDAO.findById(id);
        if (metodoExistente != null) {
            metodoPago.setId(id);  // Aseguramos que el ID se mantenga igual
            metodoPagoDAO.actualizar(metodoPago);
            return Response.ok("Método de pago actualizado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Método de pago no encontrado").build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    public Response eliminarMetodoPago(@PathParam("id") Long id) {
        MetodoPago metodoPago = metodoPagoDAO.findById(id);
        if (metodoPago != null) {
            metodoPagoDAO.delete(id);
            return Response.ok("Método de pago eliminado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Método de pago no encontrado").build();
        }
    }
}
