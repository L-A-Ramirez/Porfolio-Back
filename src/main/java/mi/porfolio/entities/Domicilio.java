package mi.porfolio.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domicilio")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String pais;
    private String provincia;
    private String localidad;

    @ManyToOne
    @JoinColumn(name = "dni_persona")
    private Persona persona;
}
