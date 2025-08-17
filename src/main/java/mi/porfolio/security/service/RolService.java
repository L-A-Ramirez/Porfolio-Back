package mi.porfolio.security.service;

import mi.porfolio.security.entity.Rol;
import mi.porfolio.security.enums.RolNombre;
import mi.porfolio.security.repository.I_RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    I_RolRepository repository;

    public Optional<Rol>getByRolNombre (RolNombre rolNombre){
        return repository.findByRolNombre(
                rolNombre);
    }

    public void save(Rol rol){
        repository.save(rol);
    }
}