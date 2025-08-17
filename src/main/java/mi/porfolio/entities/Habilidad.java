package mi.porfolio.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habilidades")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nombre;
    private Integer porcentaje;
    private String foto;
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "dni_persona", insertable = false, updatable = false)
    private Persona persona;
}