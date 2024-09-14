package com.illumio.api.matcher;

import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;

import java.util.List;
import java.util.Optional;

/**
 * Interface for matching flow log entries to tags based on lookup entries.
 */
public interface TagMatcher {

    /**
     * Finds a matching tag for the given flow log entry based on the provided lookup entries.
     *
     * @param entry {@link FlowLogEntry}. May be empty if the entry is invalid.
     * @param lookupEntries A list of {@link LookupEntry}.
     * @return The matching tag if found, or a default tag (e.g., "Untagged") if no match is found.
     */
    String findMatchingTag(Optional<FlowLogEntry> entry, List<LookupEntry> lookupEntries);
}