package py.una.pol.restaurante.restaurantemesaconsumo.rest;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ConsumoEmpleadoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoEmpleado;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/consumo-empleados")
@Produces("application/json")
@Consumes("application/json")
public class ConsumoEmpleadoREST {
    @Inject
    private ConsumoEmpleadoDAO consumoEmpleadoDAO;
    @POST
    public Response registrarConsumo(ConsumoEmpleado consumo) {
        consumoEmpleadoDAO.registrarConsumo(consumo);
        return Response.status(Response.Status.CREATED).entity(consumo).build();
    }
    @GET
    @Path("/empleado/{idEmpleado}")
    public List<ConsumoEmpleado> listarConsumosPorEmpleado(@PathParam("idEmpleado") int idEmpleado) {
        return consumoEmpleadoDAO.listarConsumosPorEmpleado(idEmpleado);
    }
}
