package com.dbp.gpt.service;

import com.dbp.gpt.config.JwtUtils;
import com.dbp.gpt.dto.LoginRequestDTO;
import com.dbp.gpt.dto.RegisterRequestDTO;
import com.dbp.gpt.entity.Empresa;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.EmpresaRepository;
import com.dbp.gpt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmpresaRepository empresaRepository;

    public AuthService(AuthenticationManager authManager, JwtUtils jwtUtils,
                       PasswordEncoder passwordEncoder, UserRepository userRepository, EmpresaRepository empresaRepository) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.empresaRepository = empresaRepository;
    }

    public String login(LoginRequestDTO loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        return jwtUtils.generateJwtToken(loginRequest.getEmail());
    }

    public String register(RegisterRequestDTO dto) {
        boolean exists = userRepository.findByEmail(dto.getEmail()).isPresent();
        if (exists) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }

        User user = new User();
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        // 🛡️ Asignar rol: si no se especifica, usar ROLE_USER
        String rolAsignado = dto.getRol() != null ? dto.getRol() : "ROLE_USER";
        user.setRol(rolAsignado);

        if (dto.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
            user.setEmpresa(empresa);
        } else if (!"ROLE_SPARKY_ADMIN".equals(rolAsignado)) {
            throw new RuntimeException("El campo empresaId es obligatorio para este rol");
        }

        userRepository.save(user);

        return "Usuario registrado con éxito";
    }
}
