package com.illumio.api.loader;

import com.illumio.model.LookupEntry;

import java.util.List;

/**
 * Interface for loading lookup table entries.
 */
public interface LookupLoader {

    /**
     * Loads lookup entries from the specified file.
     *
     * @param lookupFile  path to the lookup table file.
     * @return {@link LookupEntry}.
     */
    List<LookupEntry> loadLookupEntries(String lookupFile);
}