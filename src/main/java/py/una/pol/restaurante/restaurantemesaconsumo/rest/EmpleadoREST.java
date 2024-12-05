package py.una.pol.restaurante.restaurantemesaconsumo.rest;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.EmpleadoDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Empleado;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/empleados")
@Produces("application/json")
@Consumes("application/json")
public class EmpleadoREST {
    @Inject
    private EmpleadoDAO empleadoDAO;
    @POST
    public Response registrarEmpleado(Empleado empleado) {
        empleadoDAO.registrarEmpleado(empleado);
        return Response.status(Response.Status.CREATED).entity(empleado).build();
    }
    @GET
    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listarEmpleados();
    }
    @PUT
    @Path("/{id}")
    public Response actualizarEmpleado(@PathParam("id") int id, Empleado empleado) {
        Empleado existente = empleadoDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        empleado.setId(id);
        empleadoDAO.actualizarEmpleado(empleado);
        return Response.ok(empleado).build();
    }
}
