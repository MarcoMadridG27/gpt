package com.dbp.gpt.controller;

import com.dbp.gpt.dto.LimiteRequestDTO;
import com.dbp.gpt.entity.LimiteUsuario;
import com.dbp.gpt.entity.Restriccion;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.LimiteUsuarioRepository;
import com.dbp.gpt.repository.RestriccionRepository;
import com.dbp.gpt.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
public class CompanyAdminController {

    private final RestriccionRepository restriccionRepository;
    private final UserRepository userRepository;
    private final LimiteUsuarioRepository limiteUsuarioRepository;

    public CompanyAdminController(
            RestriccionRepository restriccionRepository,
            UserRepository userRepository,
            LimiteUsuarioRepository limiteUsuarioRepository
    ) {
        this.restriccionRepository = restriccionRepository;
        this.userRepository = userRepository;
        this.limiteUsuarioRepository = limiteUsuarioRepository;
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @GetMapping("/restricciones")
    public List<Restriccion> listarRestricciones(Authentication auth) {
        // Aquí puedes usar auth.getName() para filtrar por empresa si lo necesitas
        return restriccionRepository.findAll();
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/restricciones")
    public ResponseEntity<?> agregarRestriccion(@RequestBody Restriccion restriccion) {
        restriccionRepository.save(restriccion);
        return ResponseEntity.ok("Restricción registrada");
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/limites")
    public ResponseEntity<?> asignarLimite(@RequestBody LimiteRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        LimiteUsuario limite = new LimiteUsuario();
        limite.setUsuario(user);
        limite.setModelo(dto.getModelo());
        limite.setSolicitudesRestantes(dto.getSolicitudes());
        limite.setTokensRestantes(dto.getTokens());
        limite.setFechaUltimoReseteo(LocalDateTime.now());

        limiteUsuarioRepository.save(limite);

        return ResponseEntity.ok("✅ Límite asignado correctamente");
    }
}
