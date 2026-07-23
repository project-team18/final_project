package com.careflow.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);

    @Value("${openrouter.api.key:}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String askGemini(String message) {

        if (apiKey == null || apiKey.isBlank()) {
            logger.error("Missing OpenRouter API key configuration.");
            return "AI service is not configured. Please set OPENROUTER_API_KEY.";
        }

        String url = "https://openrouter.ai/api/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(apiKey);

        headers.set("HTTP-Referer", "http://localhost:8081");
        headers.set("X-Title", "CareFlow");

        Map<String, Object> body = Map.of(
                "model", "meta-llama/llama-3.1-8b-instruct",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", message
                        )
                )
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        try {

            ResponseEntity<Map> response =
                    restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map responseBody = response.getBody();
                if (responseBody != null && responseBody.get("choices") instanceof List<?> choices && !choices.isEmpty()) {
                    Map choice = (Map) choices.get(0);
                    if (choice != null && choice.get("message") instanceof Map msg && msg.get("content") != null) {
                        return msg.get("content").toString();
                    }
                }

                return "No response content from API";
            }

            logger.error("OpenRouter API call returned status {}", response.getStatusCode());
            return "AI service returned an error.";

        } catch (Exception e) {
            logger.error("Error calling OpenRouter API", e);
            return "Error calling AI service.";
        }
    }
}