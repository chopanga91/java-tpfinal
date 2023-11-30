package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "especialidad")
class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tecnico_especialidad")
    private ArrayList<Tecnico> tecnicos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Incidente> incidentesQueResuelve = new ArrayList<>();
    // Constructor, getters y setters

    // Constructor vacío necesario para JPA
    public Especialidad() {}

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }


    // Getters y setters

    // Métodos de utilidad para establecer y obtener la relación bidireccional
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setDireccion(String direccionEmpleado) {
        this.direccion = direccionEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }
}
