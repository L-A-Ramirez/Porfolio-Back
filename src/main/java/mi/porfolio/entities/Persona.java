package mi.porfolio.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "personas")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Persona {
    @Id
    @Column(name = "dni", nullable = false)
    private Integer dni;

    private String nombre;
    private String apellido;

    private LocalDate nacimiento;

    private String contacto;
    private String mail;
    private String profesion;

    @Column(name = "acercademi", columnDefinition = "TEXT")
    private String acercademi;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] foto;
}
