/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.entities;

import java.math.BigDecimal;

/**
 *
 * @author Verónica
 */
public class ConsumoDTO {
    private String agrupador;
    private Double cantidadVenta;
    private Double montoVenta;
    
    public ConsumoDTO(String agrupador, Double cantidadVenta, Double montoVenta) {
        this.agrupador = agrupador;
        this.cantidadVenta = cantidadVenta;
        this.montoVenta = montoVenta;
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
    
    public Double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(Double montoVenta) {
        this.montoVenta = montoVenta;
    }
}
