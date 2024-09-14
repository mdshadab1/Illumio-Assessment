package com.illumio.api.writer;

import java.util.Map;

/**
 * Interface for outputting the count and matches to output file.
 */
public interface OutputWriter {

    /**
     * Loads lookup entries from the specified file.
     *
     * @param outputFile  path to the output file.
     * @param tagCounts
     * @param portProtocolCounts
     */
    void writeOutput(String outputFile, Map<String, Integer> tagCounts, Map<String, Integer> portProtocolCounts);
}