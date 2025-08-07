package mi.porfolio.services.persona;
import mi.porfolio.entities.Persona;
import java.util.List;

public interface I_PersonaService {
    List<Persona> getAll();
    void save(Persona persona);
    void remove(Integer id);
    Persona getById(Integer id);
}
