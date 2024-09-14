package com.illumio.service.writer;

import com.illumio.api.writer.OutputWriter;
import com.illumio.exception.OutputWriteException;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class CsvOutputWriter implements OutputWriter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeOutput(String outputFile, Map<String, Integer> tagCounts, Map<String, Integer> portProtocolCounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writeCounts(writer, "Tag Counts \n Tag,Counts", tagCounts);
            writer.println(); // Adds an empty line between sections
            writeCounts(writer, "Port/Protocol Combination Counts \n Port,Protocol,Count", portProtocolCounts);
        } catch (IOException e) {
            throw new OutputWriteException("Error writing to output file: " + outputFile, e);
        }
    }

    private void writeCounts(PrintWriter writer, String header, Map<String, Integer> counts) {
        writer.println(header);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            writer.println(entry.getKey() + "," + entry.getValue());
        }
    }
}