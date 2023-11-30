package BasesDeDatos.Empresa;

import javax.persistence.*;

@Entity
@Table(name = "incidente")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;

    public void Incidente(){};

    public void Incidente(String nombre) {
        this.nombre = nombre;
    }
}
