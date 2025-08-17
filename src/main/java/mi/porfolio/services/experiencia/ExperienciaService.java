package mi.porfolio.services.experiencia;

import mi.porfolio.entities.Experiencia;
import mi.porfolio.repositories.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienciaService implements I_ExperienciaService {

    @Autowired
    private ExperienciaRepository repository;

    @Override
    public List<Experiencia> getAll() {
        return (List<Experiencia>) repository.findAll();
    }

    @Override
    public void save(Experiencia experiencia) {
        repository.save(experiencia);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Experiencia getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}