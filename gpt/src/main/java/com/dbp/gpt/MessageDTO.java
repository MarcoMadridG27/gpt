package com.dbp.gpt;

public class MessageDTO {
    private String content;

    // Constructor vacío
    public MessageDTO() {
    }

    // Constructor con contenido
    public MessageDTO(String content) {
        this.content = content;
    }

    // Getter
    public String getContent() {
        return content;
    }

    // Setter
    public void setContent(String content) {
        this.content = content;
    }
}
