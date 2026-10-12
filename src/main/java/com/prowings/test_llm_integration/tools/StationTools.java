package com.prowings.test_llm_integration.tools;

import com.prowings.test_llm_integration.model.StationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class StationTools {

    private static final Logger log =
            LoggerFactory.getLogger(StationTools.class);

    private final Map<String, StationStatus> stations = Map.of(
            "ST-101",
            new StationStatus(
                    "ST-101",
                    true,
                    "Pune Central",
                    3,
                    new BigDecimal("12.50"),
                    "INR",
                    "Sample station data"
            ),
            "ST-102",
            new StationStatus(
                    "ST-102",
                    true,
                    "Hinjewadi Hub",
                    0,
                    new BigDecimal("14.00"),
                    "INR",
                    "Sample station data"
            )
    );

    @Tool(
            name = "getStationStatus",
            description = """
                Look up a charging station by its station ID.
                Returns whether the station exists, its name,
                available connector count, and current price per kWh.
                Use this tool for station-specific availability or price.
                It does not provide charging policies, vehicle compatibility,
                waiting-time estimates, or reservations.
                """,
            returnDirect = false
    )
    public StationStatus getStationStatus(
            @ToolParam(
                    description = """
                            Charging station ID, for example ST-101 or ST-102.
                            Use the ID supplied by the user.
                            """
            )
            String stationId) {

        log.info(">>>>>>>>> TOOL EXECUTION STARTED: getStationStatus(stationId={})",
                stationId);

        if (stationId == null || stationId.isBlank()) {
            return new StationStatus(
                    stationId, false, null, null, null, null,
                    "A station ID is required"
            );
        }

        String normalizedId = stationId.trim().toUpperCase();

        StationStatus result = stations.getOrDefault(
                normalizedId,
                new StationStatus(
                        normalizedId, false, null, null, null, null,
                        "Station not found"
                )
        );

        log.info("TOOL RESULT: {}", result);
        return result;
    }
}