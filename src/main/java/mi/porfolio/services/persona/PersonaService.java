package mi.porfolio.services.persona;

import mi.porfolio.entities.Persona;
import mi.porfolio.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void guardarFoto(Integer dni, MultipartFile archivo) throws IOException {
        Optional<Persona> personaOpt = repository.findById(dni);
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            persona.setFoto(archivo.getBytes()); // guardamos el binario en la BD
            repository.save(persona);
        } else {
            throw new RuntimeException("Persona no encontrada con DNI: " + dni);
        }
    }
}
