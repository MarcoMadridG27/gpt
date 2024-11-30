package com.dbp.gpt;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
@Service
public class AzureAIClient {

    private final WebClient webClient;

    public AzureAIClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.azure.com/v1/models") // Cambia según tu endpoint
                .defaultHeader("Authorization", "Bearer " + System.getenv("GITHUB_TOKEN"))
                .build();
    }

    public String sendMessage(String input) {
        return webClient.post()
                .uri("/gpt-4/messages") // Ajusta el URI según el modelo
                .bodyValue(Map.of("content", input))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
