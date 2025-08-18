package mi.porfolio.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiencia_laboral")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String trabajo;
    private String puesto;
    private String cargo;
    private String herramientas;
    private String referencia;
    @ManyToOne
    @JoinColumn(name = "dni_persona", insertable = false, updatable = false)
    private Persona persona;

}
