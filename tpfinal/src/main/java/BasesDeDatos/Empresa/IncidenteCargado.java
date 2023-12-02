package BasesDeDatos.Empresa;

import javax.persistence.*;

@Entity
@Table(name = "incidente_cargado")
public class IncidenteCargado {
    @ManyToOne
    @JoinColumn(name = "nombre", referencedColumnName = "id")
    private Incidente incidente;
    @ManyToOne
    @JoinColumn(name = "clientes_dni", referencedColumnName = "dni")
    private Cliente cliente1;
    @ManyToOne
    @JoinColumn(name="tecnico_id", referencedColumnName = "id")
    private Tecnico tecnico;
    @Column(name="resuelto")
    private boolean resuelto;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
