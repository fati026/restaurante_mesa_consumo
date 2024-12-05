package py.una.pol.restaurante.restaurantemesaconsumo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consumo_empleados")
public class ConsumoEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @Column(nullable = false)
    private double monto;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Empleado getEmpleado() {
        return empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}


   
