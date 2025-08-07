package mi.porfolio.services.domicilio;

import mi.porfolio.entities.Domicilio;
import mi.porfolio.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements I_DomicilioService {

    @Autowired
    private DomicilioRepository repository;

    @Override
    public List<Domicilio> getAll() {
        return (List<Domicilio>) repository.findAll();
    }

    @Override
    public void save(Domicilio domicilio) {
        repository.save(domicilio);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Domicilio getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
