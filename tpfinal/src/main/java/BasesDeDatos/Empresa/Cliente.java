package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @Column(name = "dni")
    private String dni;
    @Column(name= "nombre")
    private String nombre;
    @OneToMany(mappedBy = "cliente1", cascade = CascadeType.MERGE)
    private List<IncidenteCargado> incidentes = new ArrayList<IncidenteCargado>();
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
    private Tecnico tecnico;

    public Cliente(){};

    public void addIncidente(IncidenteCargado incidenteCargado){
        this.incidentes.add(incidenteCargado);
        incidenteCargado.setCliente1(this);
    }

    public Cliente(String nombre, String dni){
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<IncidenteCargado> getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(List<IncidenteCargado> incidentes) {
        this.incidentes = incidentes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }
}
