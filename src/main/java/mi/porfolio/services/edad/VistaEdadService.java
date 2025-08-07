package mi.porfolio.services.edad;

import mi.porfolio.entities.Curso;
import mi.porfolio.entities.VistaEdad;
import mi.porfolio.repositories.VistaEdadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VistaEdadService implements I_VistaEdadService {
    @Autowired
    private VistaEdadRepository repository;


    @Override
    public List<VistaEdad> getAll() {
        return (List<VistaEdad>) repository.findAll();
    }
}
