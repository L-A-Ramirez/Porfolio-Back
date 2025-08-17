package mi.porfolio.repositories;

import mi.porfolio.entities.VistaEdad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VistaEdadRepository extends JpaRepository<VistaEdad, Integer> {
}