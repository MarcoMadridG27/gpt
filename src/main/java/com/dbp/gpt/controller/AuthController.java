package com.dbp.gpt.controller;

import com.dbp.gpt.config.JwtUtils;
import com.dbp.gpt.dto.LoginRequestDTO;
import com.dbp.gpt.dto.RegisterRequestDTO;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.UserRepository;
import com.dbp.gpt.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthService authService;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils,
                          PasswordEncoder passwordEncoder, UserRepository userRepository, AuthService authService) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        return jwtUtils.generateJwtToken(loginRequest.getEmail());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO dto) {
        return authService.register(dto);
    }

}