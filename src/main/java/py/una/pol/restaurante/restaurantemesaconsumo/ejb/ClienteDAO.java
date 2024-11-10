/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.ejb;

/**
 *
 * @author Verónica
 */
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "restaurantePU")
    private EntityManager em;
    
    public void agregar (Cliente entidad){
        this.em.persist(entidad);
    }

    public List<Cliente> lista(){
        Query q=this.em.createQuery("select * from clientes c");
        return (List<Cliente>) q.getResultList();
    }
}
