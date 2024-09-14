package com.illumio;

import com.illumio.model.FlowLogEntry;
import com.illumio.service.parser.FlowLogParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlowLogParserTest {

    private FlowLogParser parser;

    @BeforeEach
    void setUp() {
        parser = new FlowLogParser();
    }

    @Test
    void testParseValidEntry() {
        // Setup
        String logLine = "2 123456789012 eni-1a2b3c4d 10.0.1.102 172.217.7.228 1030 443 6 8 4000 1620140661 1620140721 ACCEPT OK";

        // Execute
        Optional<FlowLogEntry> result = parser.parse(logLine);

        // Verify
        assertTrue(result.isPresent());
        FlowLogEntry entry = result.get();
        assertEquals(2, entry.getVersion());
        assertEquals("123456789012", entry.getAccountId());
        assertEquals("eni-1a2b3c4d", entry.getInterfaceId());
        assertEquals("10.0.1.102", entry.getSrcAddress());
        assertEquals("172.217.7.228", entry.getDstAddress());
        assertEquals(1030, entry.getSrcPort());
        assertEquals(443, entry.getDstPort());
        assertEquals(6, entry.getProtocol());
        assertEquals(8, entry.getPackets());
        assertEquals(4000, entry.getBytes());
        assertEquals(1620140661, entry.getStartTime());
        assertEquals("ACCEPT", entry.getAction());
        assertEquals("OK", entry.getLogStatus());
    }

    @Test
    void testParseInvalidEntry() {
        // Setup
        String logLine = "Invalid log entry";

        // Execute
        Optional<FlowLogEntry> result = parser.parse(logLine);

        // Verify
        assertTrue(result.isEmpty());
    }
}
