package mi.porfolio.services.experiencia;
import mi.porfolio.entities.Experiencia;

import java.util.List;

public interface I_ExperienciaService {
    List<Experiencia> getAll();
    void save(Experiencia experiencia);
    void remove(Integer id);
    Experiencia getById(Integer id);
}
