package mi.porfolio.controllers;

import mi.porfolio.entities.Persona;
import mi.porfolio.services.persona.I_PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personas")
@CrossOrigin(origins = "https://porfolio-c8697.web.app")
public class PersonaController {

    @Autowired
    I_PersonaService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/lista")
    public List<Persona> getAll() {
        return service.getAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Persona getById(@PathVariable String id) {
        return service.getById(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable String id) {
        service.remove(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void save(@RequestBody Persona persona) {
        service.save(persona);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody Persona persona) {
        service.save(persona);
    }
}