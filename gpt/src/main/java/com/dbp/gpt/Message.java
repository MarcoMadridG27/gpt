package com.dbp.gpt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID chatId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private String aiModel;
}