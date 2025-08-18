package mi.porfolio.controllers;

        import mi.porfolio.entities.Persona;
        import mi.porfolio.services.persona.I_PersonaService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.annotation.Secured;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{dni}/foto")
    public ResponseEntity<String> subirFoto(@PathVariable Integer dni, @RequestParam("foto") MultipartFile archivo) {
        try { service.guardarFoto(dni, archivo);
            return ResponseEntity.ok("Imagen subida correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen: " + e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{dni}/foto")
    public ResponseEntity<byte[]> obtenerFoto(@PathVariable Integer dni) {
        Persona persona = service.getById(dni);
        if (persona.getFoto() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header("Content-Type", ("image/jpeg") ).body(persona.getFoto());
    }

}