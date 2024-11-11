package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MesaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Mesa;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mesas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MesaREST {

    @EJB
    private MesaDAO mesaDAO;

    // Obtener todas las mesas
    @GET
    public Response getMesas() {
        List<Mesa> mesas = mesaDAO.findAll();
        return Response.ok(mesas).build();
    }

    // Obtener una mesa por ID
    @GET
    @Path("/{id}")
    public Response getMesaById(@PathParam("id") int id) {
        Mesa mesa = mesaDAO.findById(id);
        if (mesa != null) {
            return Response.ok(mesa).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Mesa no encontrada con id: " + id)
                    .build();
        }
    }

    // Crear una nueva mesa
    @POST
    public Response createMesa(Mesa mesa) {
        mesaDAO.create(mesa);
        return Response.status(Response.Status.CREATED)
                .entity("Mesa creada con éxito con ID: " + mesa.getId())
                .build();
    }

    // Actualizar una mesa existente
    @PUT
    @Path("/{id}")
    public Response updateMesa(@PathParam("id") int id, Mesa mesa) {
        Mesa existingMesa = mesaDAO.findById(id);
        if (existingMesa != null) {
            mesa.setId(id);  // Asegura que se mantenga el ID correcto
            mesaDAO.update(mesa);
            return Response.ok("Mesa actualizada con éxito").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Mesa no encontrada con id: " + id)
                    .build();
        }
    }

    // Eliminar una mesa por ID
    @DELETE
    @Path("/{id}")
    public Response deleteMesa(@PathParam("id") int id) {
        Mesa mesa = mesaDAO.findById(id);
        if (mesa != null) {
            mesaDAO.delete(id);
            return Response.ok("Mesa eliminada con éxito").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Mesa no encontrada con id: " + id)
                    .build();
        }
    }
}
