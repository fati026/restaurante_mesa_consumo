/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.DashboardDTO;

/**
 *
 * @author Verónica
 */
public class ReporteDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public List<ConsumoDTO> generarReporteVentasPeriodo(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO(CAST(CO.fecha_creacion AS varchar), COUNT(CO.id), SUM(CO.total)) " +
                      "FROM public.consumo AS CO" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP BY CO.fecha_creacion";
        return em.createQuery(jpql, ConsumoDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<ConsumoDTO> generarReporteVentasCategoria(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO(CA.nombre, COUNT(DT.id), SUM(DT.cantidad * DT.precio_unitario)) " +
                      "FROM public.detalle_consumo AS DT" +
                      "INNER JOIN public.producto AS PR ON DT.id_producto = PR.id" +
                      "INNER JOIN public.categoria AS CA ON PR.id_categoria = CA.id" +
                      "INNER JOIN public.consumo AS CO ON DT.id_consumo = CO.id" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP BY CA.nombre";
        return em.createQuery(jpql, ConsumoDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<ConsumoDTO> generarReporteVentasEmpleado(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO(EM.nombre || ' ' || EM.apellido, COUNT(CO.id), SUM(CO.total)) " +
                      "FROM public.consumo AS CO INNER JOIN public.empleados EM ON CO.id_empleado = EM.id" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP EM.id, EM.nombre, EM.apellido";
        return em.createQuery(jpql, ConsumoDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<ConsumoDTO> generarReporteVentasCliente(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO(CL.nombre || ' ' || CL.apellido, COUNT(CO.id), SUM(CO.total)) " +
                      "FROM public.consumo AS CO INNER JOIN public.clientes AS CL ON CO.id_cliente = CL.id" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP CL.id, CL.nombre, CL.apellido";
        return em.createQuery(jpql, ConsumoDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<ConsumoDTO> generarReporteVentasMesa(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoDTO(ME.numero_mesa || ' ' || ME.area, COUNT(CO.id), SUM(CO.total)) " +
                      "FROM public.consumo AS CO INNER JOIN public.mesas ME ON CO.id_mesa = ME.id" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP ME.id, ME.numero_mesa, ME.area";
        return em.createQuery(jpql, ConsumoDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<DashboardDTO> generarReporteVentaDiaria(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.DashboardDTO(CAST(DATE(fecha_creacion) as varchar), SUM(total)) " +
                      "FROM public.consumo" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP BY DATE(fecha_creacion)" +
                      "ORDER BY DATE(fecha_creacion) DESC";
        return em.createQuery(jpql, DashboardDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<DashboardDTO> generarReporteRanking(String fechaInicio, String fechaFin) {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.DashboardDTO(PR.nombre, COUNT(DT.id)) " +
                      "FROM public.detalle_consumo AS DT" +
                      "INNER JOIN public.producto AS PR ON DT.id_producto = PR.id" +
                      "INNER JOIN public.consumo AS CO ON DT.id_consumo = CO.id" +
                      "WHERE CO.fecha_creacion BETWEEN :fechaInicio AND :fechaFin AND fecha_cierre is not null" +
                      "GROUP BY PR.nombre" +
                      "ORDER BY CantidadVenta DESC" +
                      "LIMIT 10;";
        return em.createQuery(jpql, DashboardDTO.class)
                 .setParameter("fechaInicio", LocalDate.parse(fechaInicio))
                 .setParameter("fechaFin", LocalDate.parse(fechaFin))
                 .getResultList();
    }
    
    public List<DashboardDTO> generarReporteOcupacion() {        
        String jpql = "SELECT new py.una.pol.restaurante.restaurantemesaconsumo.entities.DashboardDTO(estado, COUNT(*)) " +
                      "FROM public.mesas" +
                      "GROUP BY estado";
        return em.createQuery(jpql, DashboardDTO.class)
                 .getResultList();
    }
}
