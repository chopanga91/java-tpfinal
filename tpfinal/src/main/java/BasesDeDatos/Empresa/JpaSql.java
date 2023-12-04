package BasesDeDatos.Empresa;

import java.sql.*;
import java.util.ArrayList;

//Clase DBHelper
class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "1212";
    public static void ejecutarConsulta(String consulta) { // M�todo para ejecutar una consulta sin devolver resultados
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); // Establecer la conexi�n con la base de datos
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {// Crear la declaraci�n
                statement.executeUpdate(); // Ejecutar la consulta
            }
            connection.close(); // Cerrar la conexi�n
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet ejecutarConsultaConResultado(String consulta) { // M�todo para ejecutar una consulta y devolver un conjunto de resultados
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); // Establecer la conexi�n con la base de datos
            PreparedStatement statement = connection.prepareStatement(consulta); // Crear la declaraci�n
            return statement.executeQuery();// Ejecutar la consulta y devolver el conjunto de resultados
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}



class Clientes {

    // CHEQUEAR SI YA EXISTE X CLIENTE
    public static boolean existeCliente(String clienteDni) {

        String consultaCliente = "SELECT * FROM clientes WHERE dni = " + clienteDni;

        try (ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consultaCliente)) {
            if (resultado != null && resultado.next()) {
                String cliente_id = resultado.getString("dni");
                String nombre = resultado.getString("nombre");
                System.out.println("El cliente con dni " + cliente_id + " ya se encuentra registrado con el nombre " + nombre);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static Cliente obtenerCliente(String clienteDni) {

        String consultaCliente = "SELECT * FROM clientes WHERE dni = " + clienteDni;

        try (ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consultaCliente)) {
            if (resultado != null && resultado.next()) {
                String id = resultado.getString("dni");
                String nombre = resultado.getString("nombre");
                return new Cliente(nombre, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}




class Comerciales{


    public static ArrayList<Incidente> listadoDeIncidentes() {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        String consulta = "SELECT * FROM incidente";

        try (ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta)) {
            while (resultado != null && resultado.next()) {
                String nombre = resultado.getString("nombre");
                int tiempoResol = resultado.getInt("tiempo_resolucion");
                int especialidad_id = resultado.getInt("especialidad_id");
                Especialidad esp = new Especialidad((long) especialidad_id);
                Long id = resultado.getLong("id");
                Incidente incidente = new Incidente(nombre, tiempoResol, esp, id);
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidentes;
    }

    public static ArrayList<Tecnico> listadoDeTecnicosAptos(int idEspecialidad) {
        ArrayList<Tecnico> tecnicosAptos = new ArrayList<>();
        String consulta = "SELECT * FROM tecnico WHERE especialidad = " + idEspecialidad;

        try (ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta)) {
            while (resultado != null && resultado.next()) {
                String nombre = resultado.getString("nombre");
                String dni = resultado.getString("dni");
                Long id = resultado.getLong("id");
                //int id = resultado.getInt("id");
                tecnicosAptos.add(new Tecnico(nombre, dni, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tecnicosAptos;
    }

    public static ArrayList<Tecnico> listadoDeTecnicos() {
        ArrayList<Tecnico> tecnicos = new ArrayList<>();
        String consulta = "SELECT * FROM tecnico";

        try (ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta)) {
            while (resultado != null && resultado.next()) {
                String nombre = resultado.getString("nombre");
                String dni = resultado.getString("dni");
                Long id = resultado.getLong("id");
                int especialidad = resultado.getInt("especialidad");
                tecnicos.add(new Tecnico(nombre, dni, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tecnicos;
    }





}

class Incidentes {
    private int incidente_id;
    private int cliente_id;
    private int tecnico_id;
    private boolean resuelto;

    // Constructor

    public Incidentes(int incidente_id, int cliente_id, int tecnico_id, boolean resuelto) {
        this.incidente_id = incidente_id;
        this.cliente_id = cliente_id;
        this.tecnico_id = tecnico_id;
        this.resuelto = resuelto;
    }
}
