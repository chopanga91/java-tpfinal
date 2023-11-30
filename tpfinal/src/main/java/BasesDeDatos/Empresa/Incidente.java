package BasesDeDatos.Empresa;

import javax.persistence.*;

@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiempo_resolucion")
    private int tiempoResolucion;

    public void Incidente(){};

    public void Incidente(String nombre) {
        this.nombre = nombre;
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
}
