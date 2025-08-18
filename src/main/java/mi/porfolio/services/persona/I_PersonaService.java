package mi.porfolio.services.persona;
import mi.porfolio.entities.Persona;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface I_PersonaService {
    List<Persona> getAll();
    void save(Persona persona);
    void remove(Integer id);
    Persona getById(Integer id);
    void guardarFoto(Integer dni, MultipartFile archivo) throws IOException;
}
