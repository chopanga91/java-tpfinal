package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ListarTecnicos {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");

        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Iniciar una transacción
        entityManager.getTransaction().begin();

        try {
            // Consulta JPA para obtener la lista de empleados con sus direcciones
            String jpql = "SELECT e FROM Tecnico e JOIN FETCH e.especialidades";
            TypedQuery<Tecnico> query = entityManager.createQuery(jpql, Tecnico.class);
            List<Tecnico> tecnicos = query.getResultList();

            // Mostrar la información
            for (Tecnico tecnico : tecnicos) {
                System.out.println("Tecnico: " + tecnico.getNombre());
                if (!tecnico.especialidades.isEmpty()) {
                    for (Especialidad especialidad: tecnico.especialidades) {
                        System.out.println("Especialidad: " + especialidad);

                    }
                } else {
                    System.out.println("Sin especialidades registrada.");
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
