package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import py.una.pol.restaurante.restaurantemesaconsumo.ejb.CategoriaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Categoria;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaREST {

    @Inject
    private CategoriaDAO categoriaDAO;

    // Obtener todas las categorías
    @GET
    public List<Categoria> obtenerCategorias() {
        return categoriaDAO.findAll();
    }

    // Obtener una categoría por ID
    @GET
    @Path("/{id}")
    public Response obtenerCategoria(@PathParam("id") Long id) {
        Categoria categoria = categoriaDAO.findById(id);
        if (categoria != null) {
            return Response.ok(categoria).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Crear una nueva categoría
    @POST
    public Response crearCategoria(Categoria categoria) {
        categoriaDAO.create(categoria);
        return Response.status(Response.Status.CREATED).entity(categoria).build();
    }

    // Actualizar una categoría existente
    @PUT
    @Path("/{id}")
    public Response actualizarCategoria(@PathParam("id") Long id, Categoria categoria) {
        Categoria categoriaExistente = categoriaDAO.findById(id);
        if (categoriaExistente != null) {
            categoria.setId(id);
            categoriaDAO.update(categoria);
            return Response.ok(categoria).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Eliminar una categoría por ID
    @DELETE
    @Path("/{id}")
    public Response eliminarCategoria(@PathParam("id") Long id) {
        Categoria categoria = categoriaDAO.findById(id);
        if (categoria != null) {
            categoriaDAO.delete(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
