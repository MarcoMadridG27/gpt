package com.dbp.gpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private AzureAIClient azureAIClient;

    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        String response = azureAIClient.sendMessage(messageDTO.getContent());
        return ResponseEntity.ok(response);
    }
}

