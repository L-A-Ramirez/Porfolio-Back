package mi.porfolio.services.habilidad;

import mi.porfolio.entities.Habilidad;
import mi.porfolio.repositories.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService implements I_HabilidadService {

    @Autowired
    private HabilidadRepository repository;

    @Override
    public List<Habilidad> getAll() {
        return (List<Habilidad>) repository.findAll();
    }

    @Override
    public void save(Habilidad habilidad) {
        repository.save(habilidad);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Habilidad getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
