package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AgregarTecnico {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");

        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        // Pedir el nombre del tecnico
        System.out.print("Ingrese el nombre del tecnico: ");
        String nombreTecnico = scanner.nextLine();

        // Crear la instancia de Empleado
        Tecnico tecnico = new Tecnico();
        tecnico.setNombre(nombreTecnico);

        // Pedir la dirección del empleado
        System.out.print("Ingrese el dni de " + nombreTecnico + ": ");
        String dniEmpleado = scanner.nextLine();
        tecnico.setDni(dniEmpleado);
        // Crear la instancia de Especialidades

        Especialidad especialidad = new Especialidad();
        especialidad.setDireccion(direccionEmpleado);

        // Asignar la dirección al empleado
        empleado.setDireccion(especialidad);

        // Iniciar una transacción
        entityManager.getTransaction().begin();

        try {
            // Persistir la dirección
            entityManager.persist(especialidad);

            // Persistir el empleado
            entityManager.persist(empleado);

            // Commit de la transacción
            entityManager.getTransaction().commit();

            System.out.println("Empleado y dirección guardados exitosamente.");
        } catch (Exception e) {
            // En caso de error, hacer rollback
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
