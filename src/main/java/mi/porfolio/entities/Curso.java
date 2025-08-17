package mi.porfolio.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nombre;

    private Integer carga_horaria;

    private String centro;

    @ManyToOne
    @JoinColumn(name = "dni_persona")
    private Persona persona;
}
