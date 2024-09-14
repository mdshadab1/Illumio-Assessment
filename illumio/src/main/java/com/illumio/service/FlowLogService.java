package com.illumio.service;

import com.illumio.api.counter.Counter;
import com.illumio.api.loader.LookupLoader;
import com.illumio.api.matcher.TagMatcher;
import com.illumio.api.parser.Parser;
import com.illumio.api.writer.OutputWriter;
import com.illumio.exception.LookupTableLoadException;
import com.illumio.exception.OutputWriteException;
import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Service class responsible for processing flow logs.
 * This class orchestrates the parsing, matching, and output generation for flow logs.
 */
@Service
@AllArgsConstructor
public class FlowLogService {

    private static final Logger logger = LoggerFactory.getLogger(FlowLogService.class);

    private final Parser parser;
    private final LookupLoader lookupLoader;
    private final TagMatcher tagMatcher;
    private final OutputWriter outputWriter;
    private final Counter portProtocolCounter;

    /**
     * Processes flow logs from the specified input files and writes the results to the output file.
     *
     * @param flowLogFile path to the flow log input file.
     * @param lookupFile path to the lookup table file.
     * @param outputFile path where the output will be written.
     */
    public void processFlowLogs(String flowLogFile, String lookupFile, String outputFile) {
        List<LookupEntry> lookupEntries = loadLookupEntries(lookupFile);
        if (lookupEntries.isEmpty()) {
            return;
        }

        Map<String, Integer> tagCounts = new HashMap<>();
        List<FlowLogEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(flowLogFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Optional<FlowLogEntry> optionalEntry = parser.parse(line);
                String tag = tagMatcher.findMatchingTag(optionalEntry, lookupEntries);
                incrementTagCount(tagCounts, tag);
                optionalEntry.ifPresent(entries::add);
            }
        } catch (IOException e) {
            logger.error("Error reading flow log file: {}", e.getMessage());
            return;
        }

        Map<String, Integer> portProtocolCounts = portProtocolCounter.count(entries);

        try {
            outputWriter.writeOutput(outputFile, tagCounts, portProtocolCounts);
        } catch (OutputWriteException e) {
            logger.error("Failed to write output: {}", e.getMessage());
        }
    }

    private List<LookupEntry> loadLookupEntries(String lookupFile) {
        try {
            return lookupLoader.loadLookupEntries(lookupFile);
        } catch (LookupTableLoadException e) {
            logger.error("Failed to load lookup table: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    private void incrementTagCount(Map<String, Integer> tagCounts, String tag) {
        tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
    }
}

