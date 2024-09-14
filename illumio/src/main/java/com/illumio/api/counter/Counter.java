package com.illumio.api.counter;

import com.illumio.model.FlowLogEntry;

import java.util.List;
import java.util.Map;

/**
 * Interface for counting occurrences in flow log entries.
 */
public interface Counter {

    /**
     * Counts occurrences of specific attributes in a list of flow log entries.
     *
     * @param entries A list of {@link FlowLogEntry} objects to process.
     * @return A map.
     */
    Map<String, Integer> count(List<FlowLogEntry> entries);
}
