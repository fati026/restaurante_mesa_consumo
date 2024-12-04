/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.entities;

/**
 *
 * @author Verónica
 */
public class DashboardDTO {
    private String agrupador;
    private Double cantidadVenta;
    
    public DashboardDTO(String agrupador, Double cantidadVenta) {
        this.agrupador = agrupador;
        this.cantidadVenta = cantidadVenta;
    }
    
     public String getAgrupador() {
        return agrupador;
    }

    public void setAgrupador(String agrupador) {
        this.agrupador = agrupador;
    }
    
    public Double getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(Double cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
}
