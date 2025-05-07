package com.dbp.gpt.service;

import com.dbp.gpt.entity.SolicitudIA;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.SolicitudIARepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroService {

    private final SolicitudIARepository solicitudIARepository;

    public RegistroService(SolicitudIARepository solicitudIARepository) {
        this.solicitudIARepository = solicitudIARepository;
    }

    public void registrarSolicitudIA(User usuario, String modelo, String prompt, String respuesta, int tokensConsumidos) {
        SolicitudIA solicitud = new SolicitudIA();
        solicitud.setUsuario(usuario);
        solicitud.setEmpresa(usuario.getEmpresa());
        solicitud.setConsulta(prompt);
        solicitud.setRespuesta(respuesta);
        solicitud.setModelo(modelo);
        solicitud.setTokensConsumidos(tokensConsumidos);
        solicitud.setFecha(LocalDateTime.now());

        solicitudIARepository.save(solicitud);
    }
}
