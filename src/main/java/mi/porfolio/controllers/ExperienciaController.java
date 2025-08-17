package mi.porfolio.controllers;

import mi.porfolio.entities.Experiencia;
import mi.porfolio.services.experiencia.I_ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/experiencias")
@CrossOrigin(origins = "https://porfolio-c8697.web.app")
@EnableMethodSecurity(prePostEnabled = true)
public class ExperienciaController {

    @Autowired
    I_ExperienciaService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/lista")
    public List<Experiencia> getAll() {
        return service.getAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Experiencia getById(@PathVariable String id) {
        return service.getById(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable String id) {
        service.remove(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void save(@RequestBody Experiencia experiencia) {
        service.save(experiencia);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody Experiencia experiencia) {
        service.save(experiencia);
    }

}