package py.una.pol.restaurante.restaurantemesaconsumo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int numero_mesa; // El número de mesa debe ser único

    @Column(nullable = true)
    private String area; // Zona de la mesa (Ej: comedor, terraza)

    @Column(nullable = false)
    private int capacidad; // Número de personas que caben en la mesa

    @Column(nullable = false)
    private String estado;  // Estado de la mesa (Ej: "libre", "ocupada")

    @ManyToOne  // Relación con la entidad Cliente
    private Cliente cliente; // Clave foránea que relaciona la mesa con un cliente

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_mesa() {
        return numero_mesa;
    }

    public void setNumero_mesa(int numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numero_mesa=" + numero_mesa +
                ", area='" + area + '\'' +
                ", capacidad=" + capacidad +
                ", estado='" + estado + '\'' +
                ", id_cliente=" + (cliente != null ? cliente.getId() : null) +
                '}';
    }
}
