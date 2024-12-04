package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import py.una.pol.restaurante.restaurantemesaconsumo.ejb.MesaDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Mesa;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/mesas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MesaREST {

    @Inject
    private MesaDAO mesaDAO;

    // Obtener todas las mesas
    @GET
    public List<Mesa> getMesas() {
        return mesaDAO.findAll();
    }

    // Crear una nueva mesa
    @POST
    public Response createMesa(Mesa mesa) {
        mesaDAO.create(mesa);
        return Response.status(Response.Status.CREATED)
                .entity("Mesa creada con éxito con ID: " + mesa.getId())
                .build();
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

    // Actualizar una mesa
    @PUT
    @Path("/{id}")
    public Response updateMesa(@PathParam("id") int id, Mesa mesa) {
        mesa.setId(id); // Asegura que el ID sea el correcto
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

    // Eliminar una mesa
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

    // Cerrar una mesa
    @PUT
    @Path("/{id}/cerrar")
    public Response cerrarMesa(@PathParam("id") int id) {
        Mesa mesa = mesaDAO.findById(id);
        if (mesa != null && "ocupada".equals(mesa.getEstado())) {
            mesa.setEstado("cerrada");
            mesaDAO.update(mesa); // Se actualiza el estado a 'cerrada'
            return Response.ok("Mesa cerrada con éxito").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Mesa no encontrada o ya está cerrada").build();
        }
    }

}
