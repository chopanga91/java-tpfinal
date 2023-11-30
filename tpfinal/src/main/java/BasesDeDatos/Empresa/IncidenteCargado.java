package BasesDeDatos.Empresa;

public class IncidenteCargado {
    private Incidente incidente;
    private Cliente cliente;
    private Tecnico tecnico;
    private boolean resuelto;

    public IncidenteCargado(Incidente incidente, Cliente cliente, Tecnico tecnico) {
        this.incidente = incidente;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.resuelto = false;
    }
    public void resolverIncidente(){
        this.resuelto = true;
    }

}
