package mi.porfolio.services.curso;
import mi.porfolio.entities.Curso;
import java.util.List;

public interface I_CursoService {
    List<Curso> getAll();
    void save(Curso curso);
    void remove(Integer id);
    Curso getById(Integer id);
}
