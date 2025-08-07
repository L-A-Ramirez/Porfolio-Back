package mi.porfolio.services.educacion;

import mi.porfolio.entities.Educacion;
import mi.porfolio.repositories.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducacionService implements I_EducacionService {

    @Autowired
    private EducacionRepository repository;

    @Override
    public List<Educacion> getAll() {
        return (List<Educacion>) repository.findAll();
    }

    @Override
    public void save(Educacion educacion) {
        repository.save(educacion);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Educacion getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
