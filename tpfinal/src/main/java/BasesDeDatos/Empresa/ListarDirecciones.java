package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ListarDirecciones {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");

        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Iniciar una transacción
        entityManager.getTransaction().begin();

        try {
            // Consulta JPA para obtener la lista de empleados con sus direcciones
            String jpql = "SELECT e FROM Empleado e JOIN FETCH e.direccion";
            TypedQuery<Empleado> query = entityManager.createQuery(jpql, Empleado.class);
            List<Empleado> empleados = query.getResultList();

            // Mostrar la información
            for (Empleado empleado : empleados) {
                System.out.println("Empleado: " + empleado.getNombre());
                if (empleado.getDireccion() != null) {
                    System.out.println("Dirección: " + empleado.getDireccion().getDireccion());
                } else {
                    System.out.println("Sin dirección registrada.");
                }
                System.out.println("--------------------");
            }

            // Commit de la transacción
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Manejar la excepción
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
