package py.una.pol.restaurante.restaurantemesaconsumo.ejb;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Empleado;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class EmpleadoDAO {

    @PersistenceContext
    private EntityManager em;

    public void registrarEmpleado(Empleado empleado) {
        em.persist(empleado);
    }

    public Empleado buscarPorId(int id) {
        return em.find(Empleado.class, id);
    }

    public List<Empleado> listarEmpleados() {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    public void actualizarEmpleado(Empleado empleado) {
        em.merge(empleado);
    }

    public void eliminarEmpleado(int id) {
        Empleado empleado = buscarPorId(id);
        if (empleado != null) {
            em.remove(empleado);
        }
    }
}
