package com.illumio.api.matcher;

import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;

import java.util.List;
import java.util.Optional;

/**
 * Interface for matching strategy based on destination port and protocol.
 */
public interface TagMatchStrategy {

    /**
     * Finds matches based on the destination port and protocol of the flow log entry.
     *
     * @param entry {@link FlowLogEntry}. May be empty if the entry is invalid.
     * @param lookupEntries A list of {@link LookupEntry}.
     * @return String tags.
     */
    String match(Optional<FlowLogEntry> entry, List<LookupEntry> lookupEntries);
}
