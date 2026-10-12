package com.prowings.test_llm_integration.model;

public record RetrievedChunk(
        String id,
        String source,
        int chunk,
        String content) {
}