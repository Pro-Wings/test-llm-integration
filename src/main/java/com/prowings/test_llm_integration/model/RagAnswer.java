package com.prowings.test_llm_integration.model;

import java.util.List;

public record RagAnswer(String answer,
                        List<RetrievedChunk> retrievedEvidence) {
}
