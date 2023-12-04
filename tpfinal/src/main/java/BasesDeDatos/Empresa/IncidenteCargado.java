package BasesDeDatos.Empresa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "incidente_cargado")
public class IncidenteCargado implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "nombre", referencedColumnName = "nombre")
    private Incidente incidente;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "clientes_dni", referencedColumnName = "dni")
    private Cliente cliente1;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="tecnico_id", referencedColumnName = "id")
    private Tecnico tecnico;
    @Column(name="resuelto")
    private boolean resuelto;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    public IncidenteCargado(){};

    public IncidenteCargado(Incidente incidente, Cliente cliente, Tecnico tecnico) {
        this.incidente = incidente;
        this.cliente1 = cliente;
        this.tecnico = tecnico;
        this.resuelto = false;


    }
    public void resolverIncidente(){
        this.resuelto = true;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
