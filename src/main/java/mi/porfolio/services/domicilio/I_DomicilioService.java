package mi.porfolio.services.domicilio;
import mi.porfolio.entities.Domicilio;
import java.util.List;

public interface I_DomicilioService {
    List<Domicilio> getAll();
    void save(Domicilio domicilio);
    void remove(Integer id);
    Domicilio getById(Integer id);
}
