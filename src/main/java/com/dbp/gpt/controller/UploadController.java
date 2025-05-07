package com.dbp.gpt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImagen(@RequestParam("file") MultipartFile file) {
        try {
            // Generar nombre único
            String nombre = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Ruta donde guardar (en carpeta /uploads del proyecto)
            Path ruta = Paths.get("uploads").resolve(nombre);
            Files.createDirectories(ruta.getParent()); // Crea carpeta si no existe
            Files.copy(file.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok(nombre); // ⬅️ Devuelve el nombre para usar luego
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir archivo: " + e.getMessage());
        }
    }
}
