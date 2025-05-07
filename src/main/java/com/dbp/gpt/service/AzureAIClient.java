package com.dbp.gpt.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class AzureAIClient {

    private final WebClient webClient;

    public AzureAIClient() {
        String token = System.getenv("GITHUB_TOKEN");
        if (token == null || token.trim().isEmpty()) {
            throw new RuntimeException("❌ GITHUB_TOKEN no configurado");
        }

        this.webClient = WebClient.builder()
                .baseUrl("https://models.github.ai/inference")
                .defaultHeaders(headers -> headers.setBearerAuth(token.trim()))
                .build();
    }

    public String sendMessage(String userPrompt) {
        Map<String, Object> requestBody = Map.of(
                "model", "openai/gpt-4o",  // puedes hacerlo parámetro si soportas varios modelos
                "messages", List.of(
                        Map.of("role", "user", "content", userPrompt)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                })
                .onErrorResume(e -> {
                    System.err.println("❌ Error llamando a la API:");
                    e.printStackTrace();
                    return Mono.just("Error al obtener respuesta de la IA.");
                })
                .block();
    }
}
