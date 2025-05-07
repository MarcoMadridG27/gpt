package com.dbp.gpt.controller;

import com.dbp.gpt.dto.IAResponseDTO;
import com.dbp.gpt.dto.MessageDTO;
import com.dbp.gpt.dto.MultimodalRequestDTO;
import com.dbp.gpt.entity.SolicitudIA;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.SolicitudIARepository;
import com.dbp.gpt.repository.UserRepository;
import com.dbp.gpt.service.AzureAIClient;
import com.dbp.gpt.service.RateLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AzureAIClient azureAIClient;
    private final SolicitudIARepository solicitudIARepository;
    private final UserRepository userRepository;
    private final RateLimitService rateLimitService;

    public AIController(AzureAIClient azureAIClient,
                        SolicitudIARepository solicitudIARepository,
                        UserRepository userRepository,
                        RateLimitService rateLimitService) {
        this.azureAIClient = azureAIClient;
        this.solicitudIARepository = solicitudIARepository;
        this.userRepository = userRepository;
        this.rateLimitService = rateLimitService;
    }
    @PostMapping("/multimodal")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> multimodalChat(@RequestBody MultimodalRequestDTO dto, Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        String modelo = "openai/gpt-4o";

        String respuesta;
        int tokensConsumidos = 0;

        try {
            if (!rateLimitService.puedeRealizarSolicitud(user, modelo)) {
                respuesta = "❌ Límite de uso excedido para el modelo: " + modelo;
                saveMultimodalSolicitud(user, modelo, dto.getContent(), respuesta, tokensConsumidos, dto.getImageFilenames());
                return ResponseEntity.status(429).body(respuesta);
            }

            respuesta = azureAIClient.sendMessage(dto.getContent()); // en el futuro podrías incluir las imágenes
            saveMultimodalSolicitud(user, modelo, dto.getContent(), respuesta, tokensConsumidos, dto.getImageFilenames());
            rateLimitService.descontarLimite(user, modelo, tokensConsumidos);

            return ResponseEntity.ok(new IAResponseDTO(dto.getContent(), respuesta, modelo, tokensConsumidos));
        } catch (Exception e) {
            respuesta = "❌ Error: " + e.getMessage();
            saveMultimodalSolicitud(user, modelo, dto.getContent(), respuesta, tokensConsumidos, dto.getImageFilenames());
            return ResponseEntity.status(500).body(respuesta);
        }
    }

    private void saveMultimodalSolicitud(User user, String modelo, String consulta, String respuesta, int tokensConsumidos, List<String> archivos) {
        SolicitudIA solicitud = new SolicitudIA();
        solicitud.setUsuario(user);
        solicitud.setEmpresa(user.getEmpresa());
        solicitud.setConsulta(consulta);
        solicitud.setRespuesta(respuesta);
        solicitud.setModelo(modelo);
        solicitud.setTokensConsumidos(tokensConsumidos);
        solicitud.setFecha(LocalDateTime.now());
        solicitud.setArchivosAdjuntos(Collections.singletonList(archivos));
        solicitudIARepository.save(solicitud);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody MessageDTO messageDTO, Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        String modelo = "openai/gpt-4o";

        if (!rateLimitService.puedeRealizarSolicitud(user, modelo)) {
            return ResponseEntity.status(429).body("Límite de uso excedido para el modelo: " + modelo);
        }

        String respuesta = azureAIClient.sendMessage(messageDTO.getContent());
        int tokensConsumidos = 0; // TODO: calcular tokens correctamente

        SolicitudIA solicitud = new SolicitudIA();
        solicitud.setUsuario(user);
        solicitud.setEmpresa(user.getEmpresa());
        solicitud.setConsulta(messageDTO.getContent());
        solicitud.setRespuesta(respuesta);
        solicitud.setModelo(modelo);
        solicitud.setTokensConsumidos(tokensConsumidos);
        solicitud.setFecha(LocalDateTime.now());
        solicitudIARepository.save(solicitud);

        rateLimitService.descontarLimite(user, modelo, tokensConsumidos);

        IAResponseDTO responseDTO = new IAResponseDTO();
        responseDTO.setPrompt(messageDTO.getContent());
        responseDTO.setResponse(respuesta);
        responseDTO.setModelo(modelo);
        responseDTO.setTokensConsumidos(tokensConsumidos);
        responseDTO.setTimestamp(LocalDateTime.now().toString());

        return ResponseEntity.ok(responseDTO);
    }
}
