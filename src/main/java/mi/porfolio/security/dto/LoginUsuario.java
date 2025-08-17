package mi.porfolio.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class LoginUsuario {
    @NotNull
    private String NombreUsuario;
    @NotNull
    private String password;

}
