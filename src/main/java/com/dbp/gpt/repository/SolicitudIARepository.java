package com.dbp.gpt.repository;

import com.dbp.gpt.entity.SolicitudIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolicitudIARepository extends JpaRepository<SolicitudIA, UUID> {
}
