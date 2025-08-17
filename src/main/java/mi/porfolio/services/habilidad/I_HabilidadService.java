package mi.porfolio.services.habilidad;
import mi.porfolio.entities.Habilidad;
import java.util.List;

public interface I_HabilidadService {
    List<Habilidad> getAll();
    void save(Habilidad habilidad);
    void remove(Integer id);
    Habilidad getById(Integer id);
}
