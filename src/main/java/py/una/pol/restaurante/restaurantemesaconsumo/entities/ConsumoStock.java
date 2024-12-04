package py.una.pol.restaurante.restaurantemesaconsumo.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consumos_stock")
public class ConsumoStock implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private ProductoStock productoStock; // Relación con ProductoStock

    @ManyToOne
    @JoinColumn(name = "id_detalle_consumo", referencedColumnName = "id")
    private DetalleConsumo detalleConsumo; // Relación con DetalleConsumo

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "fecha_hora")
    private java.sql.Timestamp fechaHora; // Mantén el nombre "fecha_hora" para que coincida con la base de datos

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductoStock getProductoStock() {
        return productoStock;
    }

    public void setProductoStock(ProductoStock productoStock) {
        this.productoStock = productoStock;
    }

    public DetalleConsumo getDetalleConsumo() {
        return detalleConsumo;
    }

    public void setDetalleConsumo(DetalleConsumo detalleConsumo) {
        this.detalleConsumo = detalleConsumo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public java.sql.Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(java.sql.Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }
}
