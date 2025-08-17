package mi.porfolio.controllers;

import mi.porfolio.entities.Educacion;
import mi.porfolio.services.educacion.I_EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/educacion")
@CrossOrigin(origins = "https://porfolio-c8697.web.app")
public class EducacionController {

    @Autowired
    I_EducacionService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/lista")
    public List<Educacion> getAll() {
        return service.getAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public Educacion getById(@PathVariable String id) {
        return service.getById(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable String id) {
        service.remove(Integer.parseInt(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public void save(@RequestBody Educacion educacion) {
        service.save(educacion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public void update(@RequestBody Educacion educacion) {
        service.save(educacion);
    }

}