package mi.porfolio.services.persona;

import mi.porfolio.entities.Persona;
import mi.porfolio.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService implements I_PersonaService {

    @Autowired
    private PersonaRepository repository;

    @Override
    public List<Persona> getAll() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    public void save(Persona persona) {
        repository.save(persona);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Persona getById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
