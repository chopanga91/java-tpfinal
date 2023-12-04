package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AgregarClienteNuevo {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");
        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        Cliente cliente;

        // Pedir el nombre del Cliente
        System.out.print("Ingrese el nombre del cliente que desea incorporar: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese el dni de " + nombreCliente + ": ");

        while (true) {
            String dniCliente = scanner.nextLine();
            if (Clientes.existeCliente(dniCliente)) {
                System.out.println("Ingrese un dni diferente");
            } else {
                cliente = new Cliente(nombreCliente, dniCliente);
                System.out.println("Se cargó el nuevo cliente correctamente");
                break;
            }
        }

        // Iniciar una transacción
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();

            System.out.println("El cliente ha sido registrado correctamente");
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
