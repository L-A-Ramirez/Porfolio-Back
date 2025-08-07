package mi.porfolio.services.curso;

import mi.porfolio.entities.Curso;
import mi.porfolio.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements I_CursoService{

    @Autowired
    private CursoRepository repository;

    @Override
    public List<Curso> getAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    public void save(Curso curso) {
        repository.save(curso);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Curso getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
