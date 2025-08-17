package mi.porfolio.controllers;

import mi.porfolio.entities.Habilidad;
import mi.porfolio.services.habilidad.I_HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/habilidades")
@CrossOrigin(origins = "https://porfolio-c8697.web.app")
public class HabilidadController {

    @Autowired
    I_HabilidadService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/lista")
    public List<Habilidad> getAll() {
        return service.getAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Habilidad getById(@PathVariable String id) {
        return service.getById(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable String id) {
        service.remove(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void save(@RequestBody Habilidad habilidad) {
        service.save(habilidad);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody Habilidad habilidad) {
        service.save(habilidad);
    }

}