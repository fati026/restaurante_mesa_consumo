package py.una.pol.restaurante.restaurantemesaconsumo.entities;
import javax.persistence.*;
import java.math.BigDecimal;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Consumo;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Producto;

@Entity
public class DetalleConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_consumo", nullable = false)
    private Consumo consumo;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private int cantidad;
    private BigDecimal precioUnitario;

    // Constructor vacío
    public DetalleConsumo() {
    }

    // Constructor con parámetros
    public DetalleConsumo(Consumo consumo, Producto producto, int cantidad, BigDecimal precioUnitario) {
        this.consumo = consumo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Método para calcular el total del detalle de consumo
    //Calcula el total para este detalle de consumo 
    // multiplicando la cantidad por el precio unitario.
    public BigDecimal calcularTotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    @Override
    public String toString() {
        return "DetalleConsumo{" +
                "id=" + id +
                ", consumo=" + consumo +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", total=" + calcularTotal() +
                '}';
    }
}
