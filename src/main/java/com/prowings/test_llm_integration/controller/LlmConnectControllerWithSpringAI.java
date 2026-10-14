//package com.prowings.test_llm_integration.controller;//package com.prowings.test_llm_integration.controller;
//
////import com.prowings.test_llm_integration.service.Assistant;
//import com.prowings.test_llm_integration.service.AssistantService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LlmConnectControllerWithSpringAI {
//
//    private final AssistantService assistantService;
//
//    public LlmConnectControllerWithSpringAI(AssistantService assistantService) {
//        this.assistantService = assistantService;
//    }
//
//    @GetMapping("/prowings/chat")
//    public String chat(@RequestParam(name = "question") String userQuestion) {
//        return assistantService.chat(userQuestion);
//    }
//
//    @GetMapping("/prowings/chat-with-tools")
//    public String chatWithTools(
//            @RequestParam("question") String question) {
//
//        return assistantService.chatWithTools(question);
//    }
//}
