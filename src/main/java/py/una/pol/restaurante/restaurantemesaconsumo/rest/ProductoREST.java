package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ProductoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Producto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoREST {

    @Inject
    private ProductoDAO productoDAO;

    // Obtener todos los productos
    @GET
    public Response obtenerProductos() {
        List<Producto> productos = productoDAO.findAll();
        return Response.ok(productos).build();
    }

    // Obtener un producto por ID
    @GET
    @Path("/{id}")
    public Response obtenerProductoPorId(@PathParam("id") Long id) {
        Producto producto = productoDAO.findById(id);
        if (producto != null) {
            return Response.ok(producto).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }
    }

    // Crear un nuevo producto
    @POST
    public Response insertarProducto(Producto producto) {
        productoDAO.create(producto);
        return Response.status(Response.Status.CREATED).entity("Producto creado exitosamente").build();
    }

    // Actualizar un producto existente
    @PUT
    @Path("/{id}")
    public Response actualizarProducto(@PathParam("id") Long id, Producto producto) {
        Producto productoExistente = productoDAO.findById(id);
        if (productoExistente != null) {
            producto.setId(id);  // Aseguramos que el ID se mantenga igual
            productoDAO.update(producto);
            return Response.ok("Producto actualizado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }
    }

    // Eliminar un producto por ID
    @DELETE
    @Path("/{id}")
    public Response eliminarProducto(@PathParam("id") Long id) {
        Producto producto = productoDAO.findById(id);
        if (producto != null) {
            productoDAO.delete(id);
            return Response.ok("Producto eliminado exitosamente").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
        }
    }
}
