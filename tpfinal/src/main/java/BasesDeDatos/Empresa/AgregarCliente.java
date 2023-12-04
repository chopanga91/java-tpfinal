package BasesDeDatos.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Scanner;

//Crear un nuevo cliente con un incidente
public class AgregarCliente {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EmpleadoPersistencia");
        // Crear el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        Cliente cliente;

        // Pedir el nombre del Cliente
        System.out.print("Ingrese el nombre del cliente que desa incorporar: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese el dni de " + nombreCliente + ": ");

        while (true) {
            String dniCliente = scanner.nextLine();
            if (Clientes.existeCliente(dniCliente)) {
                System.out.println("Ingrese un dni diferente");
            } else {
                cliente = new Cliente(nombreCliente, dniCliente);
                System.out.println("Se cargo el nuevo cliente correctamente");
                break;
            }
        }

        //CREAR UN MENU DE INCIDENTES PRE EXISTENTES NUMERADOS
        System.out.println("Seleccione cual es el problema del cliente:");
        ArrayList<Incidente> listadoDeIncidentes = Comerciales.listadoDeIncidentes();
        for (int i = 0; i < listadoDeIncidentes.size(); i++) {
            System.out.println(i + 1 + " - " + listadoDeIncidentes.get(i).getNombre());
        }
        int incidenteSeleccion;
        while (true) {
            incidenteSeleccion = Integer.valueOf(scanner.nextLine()) - 1;
            if (incidenteSeleccion < 0 || incidenteSeleccion > listadoDeIncidentes.size()) {
                System.out.println("Seleccion incorrecta. Vuelva a intentarlo");

            } else {
                System.out.println("Seleccionaste " + listadoDeIncidentes.get(incidenteSeleccion).getNombre());
                System.out.println("Dicho incidente tiene un plazo estimado de resolucion de " + listadoDeIncidentes.get(incidenteSeleccion).getTiempoResolucion() + " dias");
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
            if (incidenteSeleccion < 0 || incidenteSeleccion > listadoDeIncidentes.size()) {
                System.out.println("Seleccion incorrecta. Vuelva a intentarlo");

            } else {
                break;
            }
        }
        Tecnico tecnicoSeleccionado = listadoDeTecnicosAptos.get(tecnicoSeleccion);
        cliente.setTecnico(tecnicoSeleccionado);
        Incidente incidenteCH = null;
        Cliente clienteCH = null;
        Tecnico tecnicoCH = null;
        try {
            incidenteCH = entityManager.find(Incidente.class, incidenteSeleccionado.getId());
            clienteCH = entityManager.find(Cliente.class, cliente.getDni());
            tecnicoCH = entityManager.find(Tecnico.class, tecnicoSeleccionado.getId());
            System.out.println("Incidente, cliente y tecnico seleccionados correctamente. Se procedera a persistir los datos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        IncidenteCargado incidenteCargado = new IncidenteCargado(incidenteCH, clienteCH, tecnicoCH);
        cliente.addIncidente(incidenteCargado);


        // Iniciar una transacción
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(cliente);


            entityManager.getTransaction().commit();

            System.out.println("El incidente ha sido registrado y asignado correctamente");
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