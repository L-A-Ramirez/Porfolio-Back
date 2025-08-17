package mi.porfolio.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "educacion")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String titulo;
    private String ingreso;
    private String egreso;

    @ManyToOne
    @JoinColumn(name = "dni_persona", insertable = false, updatable = false)
    private Persona persona;

    public Educacion() {
    }

    public Educacion(Integer id, String titulo, String ingreso, String egreso, Persona persona) {
        this.id = id;
        this.titulo = titulo;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.persona = persona;
    }
}

