/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.entities;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Verónica
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @Column(name = "id")
    @Basic(optional = false)
    @GeneratedValue(generator = "cliente", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clientes", sequenceName = "cliente", allocationSize = 0)
    private Long id;
    
    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    private String nombre;
    
    @Column(name = "apellido", length = 50)
    @Basic(optional = false)
    private String apellido;
    
    @Column(name = "email", length = 50)
    @Basic(optional = false)
    private String email;
    
    public Cliente() {
    }
    
    public Cliente(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', apellido=" + apellido + "', email=" + email + '}';
    }
}
