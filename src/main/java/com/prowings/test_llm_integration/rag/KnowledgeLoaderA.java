package com.prowings.test_llm_integration.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class KnowledgeLoaderA implements ApplicationRunner {

    private static final Logger log =
            LoggerFactory.getLogger(KnowledgeLoaderA.class);

    private final SimpleVectorStore vectorStore;

    public KnowledgeLoaderA(SimpleVectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ClassPathResource resource =
                new ClassPathResource("knowledge/charging-station-policy.txt");

        String text;
        try (var input = resource.getInputStream()) {
            text = StreamUtils.copyToString(
                    input, StandardCharsets.UTF_8);
        }

//        // Keep each policy section together where possible.
//        String[] sections = text.split("\\R\\s*\\R");
//
//        List<Document> documents = new ArrayList<>();
//
//        for (String section : sections) {
//            String content = section.trim();
//
//            if (!content.isEmpty()) {
//                documents.add(new Document(
//                        content,
//                        Map.of("source", "charging-station-policy.txt")
//                ));
//            }
//        }
//
//        TokenTextSplitter splitter = TokenTextSplitter.builder()
//                .withChunkSize(300)
//                .withMinChunkSizeChars(100)
//                .withMinChunkLengthToEmbed(5)
//                .build();
//
//        List<Document> chunks = splitter.apply(documents);
//        List<Document> indexedChunks = new ArrayList<>();
//
//        for (int i = 0; i < chunks.size(); i++) {
//            Document chunk = chunks.get(i);
//
//            // Prefix applies to document embeddings.
//            Document indexed = new Document(
//                    "search_document: " + chunk.getText(),
//                    Map.of(
//                            "source", "charging-station-policy.txt",
//                            "chunk", i + 1
//                    )
//            );
//
//            indexedChunks.add(indexed);
//
//            log.info("INDEXING chunk {}: {}",
//                    i + 1, chunk.getText());
//        }
//
//        // This generates embeddings and stores the indexed documents.
//        vectorStore.add(indexedChunks);
//
//        log.info("RAG indexing complete: {} chunks",
//                indexedChunks.size());


// Split only at a line containing "---".
        String[] policies = text.split("(?m)^\\h*---\\h*\\r?$");

        List<Document> indexedPolicies = new ArrayList<>();

        for (String policy : policies) {
            String content = policy.trim();

            if (content.isEmpty()) {
                continue;
            }

            // The first line becomes the policy title.
            String title = content.lines()
                    .findFirst()
                    .orElse("Untitled policy");

            int chunkNumber = indexedPolicies.size() + 1;

            Document document = new Document(
                    "search_document: " + content,
                    Map.of(
                            "source", "charging-station-policy.txt",
                            "policy", title,
                            "chunk", chunkNumber
                    )
            );

            indexedPolicies.add(document);

            log.info("INDEXING policy chunk {} [{}]:\n{}",
                    chunkNumber, title, content);
        }

        vectorStore.add(indexedPolicies);

        log.info("RAG indexing complete: {} policy chunks",
                indexedPolicies.size());

    }
}