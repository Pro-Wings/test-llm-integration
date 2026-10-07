package com.prowings.test_llm_integration.model;

import java.math.BigDecimal;

public record StationStatus(
        String stationId,
        boolean found,
        String stationName,
        Integer availableConnectors,
        BigDecimal pricePerKwh,
        String currency,
        String message
) {
}