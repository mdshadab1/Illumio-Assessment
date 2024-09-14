package com.illumio;

import com.illumio.model.FlowLogEntry;
import com.illumio.service.counter.PortProtocolCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PortProtocolCounterTest {

    private PortProtocolCounter counter;

    @BeforeEach
    void setUp() {
        counter = new PortProtocolCounter();
    }

    @Test
    void testCount() {
        // Setup
        List<FlowLogEntry> entries = Arrays.asList(
                new FlowLogEntry(2, "123", "eni-1", "10.0.0.1", "192.168.0.1", 12345, 80, 6, 100, 1000L, 1620000000L, 1620000060L, "ACCEPT", "OK"),
                new FlowLogEntry(2, "123", "eni-2", "10.0.0.2", "192.168.0.2", 12346, 443, 6, 200, 2000L, 1620000000L, 1620000060L, "ACCEPT", "OK"),
                new FlowLogEntry(2, "123", "eni-3", "10.0.0.3", "192.168.0.3", 12347, 80, 6, 300, 3000L, 1620000000L, 1620000060L, "ACCEPT", "OK")
        );

        // Execute
        Map<String, Integer> result = counter.count(entries);

        // Verify
        assertEquals(2, result.size());
        assertEquals(2, result.get("80,tcp"));
        assertEquals(1, result.get("443,tcp"));
    }
}
