package py.una.pol.restaurante.restaurantemesaconsumo.ejb;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.ConsumoEmpleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class ConsumoEmpleadoDAO {
    @PersistenceContext
    private EntityManager em;
    public void registrarConsumo(ConsumoEmpleado consumo) {
        em.persist(consumo);
    }
    public List<ConsumoEmpleado> listarConsumosPorEmpleado(int idEmpleado) {
        return em.createQuery(
                "SELECT c FROM ConsumoEmpleado c WHERE c.empleado.id = :idEmpleado", 
                ConsumoEmpleado.class
        ).setParameter("idEmpleado", idEmpleado).getResultList();
    }
}
