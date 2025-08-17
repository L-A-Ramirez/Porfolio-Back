package mi.porfolio.security.controller;

import jakarta.validation.Valid;
import mi.porfolio.security.dto.JwtDto;
import mi.porfolio.security.dto.LoginUsuario;
import mi.porfolio.security.dto.NuevoUsuario;
import mi.porfolio.security.entity.Rol;
import mi.porfolio.security.entity.Usuario;
import mi.porfolio.security.enums.RolNombre;
import mi.porfolio.security.jwt.JwtProvider;
import mi.porfolio.security.service.RolService;
import mi.porfolio.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("Campos mal ingresados o email inválido"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity<>(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity<>(new Mensaje("Ese email ya está registrado"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword())
        );

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).orElseThrow(
                () -> new RuntimeException("Error: El rol USER no fue encontrado.")
        ));

        if (nuevoUsuario.getRoles() != null && nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).orElseThrow(
                    () -> new RuntimeException("Error: El rol ADMIN no fue encontrado.")
            ));
        }

        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<>(new Mensaje("Usuario guardado con éxito"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("Campos mal ingresados"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getNombreUsuario(),
                        loginUsuario.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(authentication.getName());

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
