package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "tecnico")
class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dni")
    private String dni;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tecnico_especialidad")
    public ArrayList<Especialidad> especialidades = new ArrayList<>();

    // Constructor, getters y setters

    // Constructor vacío necesario para JPA
    public Tecnico() {}

    public Tecnico(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Getters y setters

    // Métodos de utilidad para establecer y obtener la relación bidireccional
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void addEspecialidad(Especialidad especialidad){
        if(this.especialidades.contains(especialidad)){
            return;
        } else {
            this.especialidades.add(especialidad);
        }

    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombreTecnico) {
        this.nombre = nombreTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tecnico tecnico = (Tecnico) o;
        return nombre.equals(tecnico.nombre) && dni.equals(tecnico.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni);
    }
}
