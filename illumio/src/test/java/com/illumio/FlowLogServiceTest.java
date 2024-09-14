package com.illumio;

import com.illumio.service.FlowLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlowLogServiceTest {

    @Autowired
    private FlowLogService flowLogService;

    @Test
    void testProcessFlowLogs() throws IOException {
        // Prepare test files
        String flowLogFile = "test_flow_logs.txt";
        String lookupFile = "test_lookup_table.csv";
        String outputFile = "test_output.txt";

        // Create test flow log file
        Files.write(Paths.get(flowLogFile),
                "2 123456789012 eni-1a2b3c4d 10.0.1.102 172.217.7.228 1030 443 6 8 4000 1620140661 1620140721 ACCEPT OK".getBytes());

        // Create test lookup file
        Files.write(Paths.get(lookupFile),
                "dstport,protocol,tag\n443,tcp,sv_P2".getBytes());

        // Process logs
        flowLogService.processFlowLogs(flowLogFile, lookupFile, outputFile);

        // Check if output file exists and contains expected content
        File output = new File(outputFile);
        assertTrue(output.exists());
        String content = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertTrue(content.contains("sv_P2,1"));
        assertTrue(content.contains("443,tcp,1"));

        // Clean up test files
        Files.deleteIfExists(Paths.get(flowLogFile));
        Files.deleteIfExists(Paths.get(lookupFile));
        Files.deleteIfExists(Paths.get(outputFile));
    }
}
