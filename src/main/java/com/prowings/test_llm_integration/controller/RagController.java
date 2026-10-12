package com.prowings.test_llm_integration.controller;

import java.util.List;

import com.prowings.test_llm_integration.model.RagAnswer;
import com.prowings.test_llm_integration.model.RetrievedChunk;
import com.prowings.test_llm_integration.service.RagService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/prowings/rag")
public class RagController {

    private final RagService ragService;

    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    @GetMapping("/retrieve")
    public List<RetrievedChunk> retrieve(
            @RequestParam("question") String question) {

        return ragService.retrieve(validate(question));
    }

    @GetMapping("/ask")
    public RagAnswer ask(
            @RequestParam("question") String question) {

        return ragService.ask(validate(question));
    }

    private String validate(String question) {

        if (question.isBlank() || question.length() > 1000) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Provide a question between 1 and 1000 characters");
        }

        return question.trim();
    }
}