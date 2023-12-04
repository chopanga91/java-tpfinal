package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "especialidad")
class Especialidad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "especialidad")
    private List<Tecnico> tecnicos = new ArrayList<Tecnico>();

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Incidente> incidentesQueResuelve = new ArrayList<Incidente>();
    // Constructor, getters y setters

    // Constructor vacío necesario para JPA
    public Especialidad() {}

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }
    public Especialidad(Long id) {
        this.id = id;
    }

    public Especialidad(String nombre, Tecnico tecnico) {
        this.nombre = nombre;
        this.tecnicos.add(tecnico);
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void addIncidentesQueResuelve(Incidente incidente){
        this.incidentesQueResuelve.add(incidente);
    }


    public void addTecnico(Tecnico tecnico){

        this.tecnicos.add(tecnico);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especialidad that = (Especialidad) o;
        return nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    // Métodos de utilidad para establecer y obtener la relación bidireccional
//    public void setNombre(Empleado empleado) {
//        this.empleado = empleado;
//    }
//
//    public Empleado getEmpleado() {
//        return empleado;
//    }
//
//    public void setDireccion(String direccionEmpleado) {
//        this.direccion = direccionEmpleado;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }


    public Long getId() {
        return id;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(List<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public List<Incidente> getIncidentesQueResuelve() {
        return incidentesQueResuelve;
    }

    public void setIncidentesQueResuelve(List<Incidente> incidentesQueResuelve) {
        this.incidentesQueResuelve = incidentesQueResuelve;
    }
}
