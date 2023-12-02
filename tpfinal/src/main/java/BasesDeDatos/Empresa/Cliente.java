package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Id
    @Column(name = "dni")
    private int dni;
    @OneToMany
    private List<IncidenteCargado> incidentes = new ArrayList<IncidenteCargado>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
    private Tecnico tecnico;

    public Cliente(){};

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
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
