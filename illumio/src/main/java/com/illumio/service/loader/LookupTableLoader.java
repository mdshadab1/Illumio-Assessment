package com.illumio.service.loader;

import com.illumio.api.loader.LookupLoader;
import com.illumio.exception.LookupTableLoadException;
import com.illumio.model.LookupEntry;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LookupTableLoader implements LookupLoader {

    /**
     * {@inheritDoc}
     */
    public List<LookupEntry> loadLookupEntries(String lookupFile) {
        List<LookupEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lookupFile))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    entries.add(new LookupEntry(Integer.parseInt(parts[0]), parts[1], parts[2]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new LookupTableLoadException("Error loading lookup table", e);
        }
        return entries;
    }
}