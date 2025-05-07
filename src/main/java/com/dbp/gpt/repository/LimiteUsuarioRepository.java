// repository/LimiteUsuarioRepository.java
package com.dbp.gpt.repository;

import com.dbp.gpt.entity.LimiteUsuario;
import com.dbp.gpt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LimiteUsuarioRepository extends JpaRepository<LimiteUsuario, UUID> {
    Optional<LimiteUsuario> findByUsuarioAndModelo(User usuario, String modelo);
}
