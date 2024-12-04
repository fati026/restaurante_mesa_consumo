/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.entities;

/**
 *
 * @author Verónica
 */
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;
    
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fecha_hora;
    
    @Column(name = "cantidad_personas", nullable = false)
    private int cantidad_personas;
    
    @Column(name = "contacto", nullable = false)
    private String contacto;  
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public LocalDateTime getFechaHora() {
        return fecha_hora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fecha_hora = fechaHora;
    }
    
    public int getCantidadPersonas() {
        return cantidad_personas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidad_personas = cantidadPersonas;
    }
    
     public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
