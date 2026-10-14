//package com.prowings.test_llm_integration.service;
//
//import com.prowings.test_llm_integration.tools.StationTools;
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AssistantService {
//
//    private final ChatClient chatClient;
//    private final StationTools stationTools;
//
//    public AssistantService(
//            ChatClient.Builder builder,
//            StationTools stationTools) {
//
//        this.stationTools = stationTools;
//
//        this.chatClient = builder
//                .defaultSystem("""
//                        You are a helpful programming and charging-station
//                        assistant.
//
//                        For a specific station's availability or tariff,
//                        always use getStationStatus.
//                        Never invent station data.
//
//                        If the user has not supplied a station ID,
//                        ask for it before looking up a station.
//
//                        If the tool reports found=false,
//                        explain that the station could not be found.
//
//                        Keep answers concise.
//                        """)
//                .build();
//    }
//    public String chat(String userMessage) {
//        return chatClient.prompt()
//                .user(userMessage)
//                .call()
//                .content();
//    }
//
//    public String chatWithTools(String userMessage) {
//        return chatClient.prompt()
//                .user(userMessage)
//                .tools(stationTools)
//                .call()
//                .content();
//    }
//}