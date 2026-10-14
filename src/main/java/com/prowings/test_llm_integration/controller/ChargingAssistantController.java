package com.prowings.test_llm_integration.controller;

import java.util.List;

import com.prowings.test_llm_integration.service.ChargingAssistantServiceWithRagAndMCP.ChargingAnswer;
import com.prowings.test_llm_integration.service.ChargingAssistantServiceWithRagAndMCP.ToolInfo;

import com.prowings.test_llm_integration.service.ChargingAssistantServiceWithRagAndMCP;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/prowings/charging-assistant")
public class ChargingAssistantController {

    private final ChargingAssistantServiceWithRagAndMCP assistantService;

    public ChargingAssistantController(
            ChargingAssistantServiceWithRagAndMCP assistantService) {

        this.assistantService = assistantService;
    }

    @GetMapping("/tools")
    public List<ChargingAssistantServiceWithRagAndMCP.ToolInfo> tools() {
        return assistantService.availableTools();
    }

    @GetMapping("/ask")
    public ChargingAssistantServiceWithRagAndMCP.ChargingAnswer ask(
            @RequestParam("question") String question) {

        if (question.isBlank() || question.length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Provide a question between 1 and 1000 characters");
        }

        return assistantService.ask(question.trim());
    }
}