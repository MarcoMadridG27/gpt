package com.dbp.gpt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID userId;
    private String chatName;
    private LocalDate dateCreation;
}
