package com.illumio;

import com.illumio.exception.OutputWriteException;
import com.illumio.service.writer.CsvOutputWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvOutputWriterTest {

    private CsvOutputWriter writer;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        writer = new CsvOutputWriter();
    }

    @Test
    void testWriteOutput() {
        // Setup
        Path outputFile = tempDir.resolve("output.csv");
        Map<String, Integer> tagCounts = new HashMap<>();
        tagCounts.put("web", 10);
        tagCounts.put("secure-web", 5);

        Map<String, Integer> portProtocolCounts = new HashMap<>();
        portProtocolCounts.put("80,tcp", 10);
        portProtocolCounts.put("443,tcp", 5);

        // Execute
        writer.writeOutput(outputFile.toString(), tagCounts, portProtocolCounts);

        // Verify
        assertTrue(outputFile.toFile().exists());
        // You might want to read the file contents and verify its structure here
    }

    @Test
    void testWriteOutputWithInvalidPath() {
        // Setup
        String invalidPath = "/invalid/path/output.csv";
        Map<String, Integer> tagCounts = new HashMap<>();
        Map<String, Integer> portProtocolCounts = new HashMap<>();

        // Execute & Verify
        assertThrows(OutputWriteException.class, () ->
                writer.writeOutput(invalidPath, tagCounts, portProtocolCounts)
        );
    }
}
