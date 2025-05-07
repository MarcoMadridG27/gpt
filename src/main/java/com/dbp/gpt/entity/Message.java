package com.dbp.gpt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID chatId;

    @Column(nullable = false)
    private String sender; // "user" o "assistant"

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private String aiModel;

    // Constructor vacío requerido por JPA
    public Message() {}

    public Message(UUID chatId, String sender, String content, String aiModel) {
        this.chatId = chatId;
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.aiModel = aiModel;
    }

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public UUID getChatId() {
        return chatId;
    }

    public void setChatId(UUID chatId) {
        this.chatId = chatId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAiModel() {
        return aiModel;
    }

    public void setAiModel(String aiModel) {
        this.aiModel = aiModel;
    }
}
