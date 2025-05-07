
// service/RateLimitService.java
package com.dbp.gpt.service;

import com.dbp.gpt.entity.LimiteUsuario;
import com.dbp.gpt.entity.User;
import com.dbp.gpt.repository.LimiteUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    private final LimiteUsuarioRepository limiteUsuarioRepository;

    public RateLimitService(LimiteUsuarioRepository limiteUsuarioRepository) {
        this.limiteUsuarioRepository = limiteUsuarioRepository;
    }

    public boolean puedeRealizarSolicitud(User usuario, String modelo) {
        return limiteUsuarioRepository.findByUsuarioAndModelo(usuario, modelo)
                .map(limite -> limite.getSolicitudesRestantes() > 0 && limite.getTokensRestantes() > 0)
                .orElse(true); // si no hay límites registrados, permitir
    }

    public void descontarLimite(User usuario, String modelo, int tokensConsumidos) {
        limiteUsuarioRepository.findByUsuarioAndModelo(usuario, modelo).ifPresent(limite -> {
            limite.setSolicitudesRestantes(limite.getSolicitudesRestantes() - 1);
            limite.setTokensRestantes(limite.getTokensRestantes() - tokensConsumidos);
            limiteUsuarioRepository.save(limite);
        });
    }
}
