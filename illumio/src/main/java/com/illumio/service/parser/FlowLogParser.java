package com.illumio.service.parser;

import com.illumio.api.parser.Parser;
import com.illumio.exception.InvalidFlowLogEntryException;
import com.illumio.model.FlowLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FlowLogParser implements Parser {
    private static final Logger logger = LoggerFactory.getLogger(FlowLogParser.class);

    /**
     * Parses a single line of an AWS VPC flow log and converts it into a {@link FlowLogEntry} object.
     *
     * @param line one line from flow log entry.
     * @return A {@link FlowLogEntry}.
     */
    public Optional<FlowLogEntry> parse(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length < 14) {
            logger.warn("Invalid flow log entry: insufficient parts. Line: {}", line);
            return Optional.empty();
        }

        try {
            int version = Integer.parseInt(parts[0]);
            String accountId = parts[1];
            String interfaceId = parts[2];
            String srcAddress = parts[3];
            String dstAddress = parts[4];
            int srcPort = Integer.parseInt(parts[5]);
            int dstPort = Integer.parseInt(parts[6]);
            int protocol = Integer.parseInt(parts[7]);
            int packets = Integer.parseInt(parts[8]);
            long bytes = Long.parseLong(parts[9]);
            long startTime = Long.parseLong(parts[10]);
            long endTime = Long.parseLong(parts[11]);
            String action = parts[12];
            String logStatus = parts[13];
            FlowLogEntry entry = new FlowLogEntry(version, accountId, interfaceId, srcAddress, dstAddress,
                    srcPort, dstPort, protocol, packets, bytes,
                    startTime, endTime, action, logStatus);

            return Optional.of(entry);
        } catch (NumberFormatException e) {
            throw new InvalidFlowLogEntryException("Error parsing flow log entry: " + line, e);
        }
    }
}
