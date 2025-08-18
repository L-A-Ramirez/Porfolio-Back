package mi.porfolio.security.service;

import mi.porfolio.security.entity.Usuario;
import mi.porfolio.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public Optional<Usuario> getByNombreUsuario (String nombreUsuario){
        return repository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario (String nombreUsuario){
        return repository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail (String email){
        return repository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        repository.save(usuario);
    }
}