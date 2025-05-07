package com.dbp.gpt.repository;

import com.dbp.gpt.entity.Restriccion;
import com.dbp.gpt.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestriccionRepository extends JpaRepository<Restriccion, UUID> {
    List<Restriccion> findByEmpresa(Empresa empresa);
}
