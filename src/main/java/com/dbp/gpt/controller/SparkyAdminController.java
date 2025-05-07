package com.dbp.gpt.controller;

import com.dbp.gpt.entity.Empresa;
import com.dbp.gpt.repository.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class SparkyAdminController {

    private final EmpresaRepository empresaRepository;

    public SparkyAdminController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @PreAuthorize("hasRole('SPARKY_ADMIN')")
    @GetMapping("/empresas")
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    @PreAuthorize("hasRole('SPARKY_ADMIN')")
    @PostMapping("/empresas")
    public ResponseEntity<?> registrarEmpresa(@RequestBody Empresa empresa) {
        empresaRepository.save(empresa);
        return ResponseEntity.ok("Empresa registrada con éxito");
    }

    @PreAuthorize("hasRole('SPARKY_ADMIN')")
    @PutMapping("/empresas/{id}/activar")
    public ResponseEntity<?> activarEmpresa(@PathVariable UUID id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow();
        empresa.setActiva(true);
        empresaRepository.save(empresa);
        return ResponseEntity.ok("Empresa activada");
    }

    @PreAuthorize("hasRole('SPARKY_ADMIN')")
    @PutMapping("/empresas/{id}/desactivar")
    public ResponseEntity<?> desactivarEmpresa(@PathVariable UUID id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow();
        empresa.setActiva(false);
        empresaRepository.save(empresa);
        return ResponseEntity.ok("Empresa desactivada");
    }
}
