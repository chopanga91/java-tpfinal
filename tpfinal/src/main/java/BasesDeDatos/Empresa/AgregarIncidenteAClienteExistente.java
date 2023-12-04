package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Scanner;

public class AgregarIncidenteAClienteExistente {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");
        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        Cliente cliente;

        // Pedir el dni del Cliente existente
        System.out.print("Ingrese el dni del cliente existente: ");
        String dniClienteExistente = scanner.nextLine();

        cliente = entityManager.find(Cliente.class, dniClienteExistente);

        if (cliente != null) {
            //CREAR UN MENU DE INCIDENTES PRE EXISTENTES NUMERADOS
            System.out.println("Seleccione cuál es el problema del cliente:");
            ArrayList<Incidente> listadoDeIncidentes = Comerciales.listadoDeIncidentes();
            for (int i = 0; i < listadoDeIncidentes.size(); i++) {
                System.out.println(i + 1 + " - " + listadoDeIncidentes.get(i).getNombre());
            }
            int incidenteSeleccion;
            while (true) {
                incidenteSeleccion = Integer.valueOf(scanner.nextLine()) - 1;
                if (incidenteSeleccion < 0 || incidenteSeleccion > listadoDeIncidentes.size()) {
                    System.out.println("Selección incorrecta. Vuelva a intentarlo");

                } else {
                    System.out.println("Seleccionaste " + listadoDeIncidentes.get(incidenteSeleccion).getNombre());
                    System.out.println("Dicho incidente tiene un plazo estimado de resolución de " + listadoDeIncidentes.get(incidenteSeleccion).getTiempoResolucion() + " días");
                    break;
                }
            }
            Incidente incidenteSeleccionado = listadoDeIncidentes.get(incidenteSeleccion);

            //CONSULTAR QUE TECNICO POSEE LA ESPECIALIDAD PARA SOLUCIONAR DICHO INCIDENTE
            System.out.println("Dispone del/los siguientes tecnicos. Seleccione uno:");
            ArrayList<Tecnico> listadoDeTecnicosAptos = Comerciales.listadoDeTecnicosAptos(Math.toIntExact(incidenteSeleccionado.getEspecialidad().getId()));
            for (int i = 0; i < listadoDeTecnicosAptos.size(); i++) {
                System.out.println(i + 1 + " - " + listadoDeTecnicosAptos.get(i).getNombre());
            }
            int tecnicoSeleccion;
            while (true) {
                tecnicoSeleccion = Integer.valueOf(scanner.nextLine()) - 1;
                if (tecnicoSeleccion < 0 || tecnicoSeleccion > listadoDeTecnicosAptos.size()) {
                    System.out.println("Selección incorrecta. Vuelva a intentarlo");

                } else {
                    break;
                }
            }
            Tecnico tecnicoSeleccionado = listadoDeTecnicosAptos.get(tecnicoSeleccion);
            cliente.setTecnico(tecnicoSeleccionado);

            // Iniciar una transacción
            entityManager.getTransaction().begin();

            try {
                IncidenteCargado incidenteCargado = new IncidenteCargado(incidenteSeleccionado, cliente, tecnicoSeleccionado);
                cliente.addIncidente(incidenteCargado);
                entityManager.persist(incidenteCargado);

                entityManager.getTransaction().commit();

                System.out.println("El incidente ha sido registrado y asignado correctamente al cliente existente");
            } catch (Exception e) {
                // En caso de error, hacer rollback
                entityManager.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                // Cerrar el EntityManager y el EntityManagerFactory
                entityManager.close();
                entityManagerFactory.close();
            }
        } else {
            System.out.println("No se encontró un cliente con el DNI proporcionado.");
        }
    }
}
