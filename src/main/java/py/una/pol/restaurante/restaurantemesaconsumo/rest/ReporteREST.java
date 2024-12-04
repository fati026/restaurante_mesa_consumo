/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.rest;

/**
 *
 * @author Verónica
 */
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ReporteDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.DashboardDTO;

@Path("/reportes")
@Produces(MediaType.APPLICATION_JSON)

public class ReporteREST {
    @EJB
    private ReporteDAO reporteDAO;

    @GET
    @Path("/ventasperiodo")
    public Response generarReporteVentasPeriodo(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<ConsumoDTO> reporte = reporteDAO.generarReporteVentasPeriodo(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/ventascategoria")
    public Response generarReporteVentasCategoria(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<ConsumoDTO> reporte = reporteDAO.generarReporteVentasCategoria(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/ventasempleado")
    public Response generarReporteVentasEmpleado(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<ConsumoDTO> reporte = reporteDAO.generarReporteVentasEmpleado(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/ventascliente")
    public Response generarReporteVentasCliente(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<ConsumoDTO> reporte = reporteDAO.generarReporteVentasCliente(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/ventasmesa")
    public Response generarReporteVentasMesa(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<ConsumoDTO> reporte = reporteDAO.generarReporteVentasMesa(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/dashboardventa")
    public Response generarReporteVentaDiaria(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<DashboardDTO> reporte = reporteDAO.generarReporteVentaDiaria(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/dashboardranking")
    public Response generarReporteRanking(@QueryParam("fechaInicio") String fechaInicio,
                                         @QueryParam("fechaFin") String fechaFin) {
        List<DashboardDTO> reporte = reporteDAO.generarReporteRanking(fechaInicio, fechaFin);
        return Response.ok(reporte).build();
    }
    
    @GET
    @Path("/dashboardocupacion")
    public Response generarReporteOcupacion() {
        List<DashboardDTO> reporte = reporteDAO.generarReporteOcupacion();
        return Response.ok(reporte).build();
    }
}
