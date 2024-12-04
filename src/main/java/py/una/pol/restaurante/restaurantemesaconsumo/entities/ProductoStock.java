package py.una.pol.restaurante.restaurantemesaconsumo.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos_stock")
public class ProductoStock implements Serializable {
    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "stock_actual")
    private Integer stockActual;

    @Column(name = "stock_minimo")
    private Integer stockMinimo;

   public int getStockActual() {
    return stockActual;  // Devuelve el stock actual del producto
}

public void setStockActual(int stockActual) {
    this.stockActual = stockActual;
}

public int getStockMinimo() {
    return stockMinimo;  // Devuelve el stock mínimo del producto
}

public void setStockMinimo(int stockMinimo) {
    this.stockMinimo = stockMinimo;
}

 public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
