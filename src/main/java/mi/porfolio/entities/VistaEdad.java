package mi.porfolio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "vista_edad")
@Getter @Setter
public class VistaEdad {

    @Id
    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "edad")
    private Integer edad;

}
