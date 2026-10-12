package com.prowings.test_llm_integration.controller;

import com.prowings.test_llm_integration.service.ChargingAssistantService;
import com.prowings.test_llm_integration.service.ChargingAssistantService.ChargingAnswer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/prowings/charging-assistant")
public class ChargingAssistantController {

    private final ChargingAssistantService assistantService;

    public ChargingAssistantController(
            ChargingAssistantService assistantService) {

        this.assistantService = assistantService;
    }

    @GetMapping("/ask")
    public ChargingAnswer ask(
            @RequestParam("question") String question) {

        if (question.isBlank() || question.length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Provide a question between 1 and 1000 characters");
        }

        return assistantService.ask(question.trim());
    }
}