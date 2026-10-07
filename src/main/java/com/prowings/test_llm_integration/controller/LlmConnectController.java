//package com.prowings.test_llm_integration.controller;
//
//import com.prowings.test_llm_integration.service.Assistant;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class LlmConnectController {
//
//    private Assistant assistant;
//
//    public LlmConnectController(Assistant assistant) {
//        this.assistant = assistant;
//    }
//
//    @GetMapping("/prowings/chat")
//    public String chat(@RequestParam(name = "question") String userQuestion)
//    {
//        System.out.println(">>>>>> User Question: " + userQuestion);
//        return assistant.chat(userQuestion);
//    }
//}
