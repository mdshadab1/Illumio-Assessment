package com.illumio.api.parser;

import com.illumio.model.FlowLogEntry;

import java.util.Optional;

/**
 * Interface for parsing flow log entries.
 */
public interface Parser {

    /**
     * Parses a single line of a flow log and converts it into a {@link FlowLogEntry} object.
     *
     * @param line One line from flow log entry.
     * @return {@link FlowLogEntry}.
     */
    Optional<FlowLogEntry> parse(String line);
}