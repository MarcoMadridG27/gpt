package com.dbp.gpt.dto;

import java.util.List;

public class MultimodalRequestDTO {
    private String content; // Texto de la consulta
    private List<String> imageFilenames; // Nombres de imágenes subidas

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageFilenames() {
        return imageFilenames;
    }

    public void setImageFilenames(List<String> imageFilenames) {
        this.imageFilenames = imageFilenames;
    }
}
