package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidente")
public class Incidente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiempo_resolucion")
    private int tiempoResolucion;

    @ManyToOne
    @JoinColumn(name = "especialidad_id", referencedColumnName = "id")
    private Especialidad especialidad;
    @OneToMany(mappedBy = "incidente",cascade = CascadeType.ALL)
    private List<IncidenteCargado> incidenteCargadoList = new ArrayList<IncidenteCargado>();




    public Incidente(){};

    public Incidente(String nombre, int tiempoRes, Especialidad especialidad, Long id) {
        this.nombre = nombre;
        this.tiempoResolucion = tiempoRes;
        this.especialidad = especialidad;
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoResolucion() {
        return tiempoResolucion;
    }

    public void setTiempoResolucion(int tiempoResolucion) {
        this.tiempoResolucion = tiempoResolucion;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = Long.valueOf(id);
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
