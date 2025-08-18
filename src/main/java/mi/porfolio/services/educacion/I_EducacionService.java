package mi.porfolio.services.educacion;
import mi.porfolio.entities.Educacion;
import java.util.List;

public interface I_EducacionService {
    List<Educacion> getAll();
    void save(Educacion educacion);
    void remove(Integer id);
    Educacion getById(Integer id);
}
