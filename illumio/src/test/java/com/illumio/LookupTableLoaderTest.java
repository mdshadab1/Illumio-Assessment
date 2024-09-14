package com.illumio;

import com.illumio.exception.LookupTableLoadException;
import com.illumio.model.LookupEntry;
import com.illumio.service.loader.LookupTableLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LookupTableLoaderTest {

    private LookupTableLoader loader;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        loader = new LookupTableLoader();
    }

    @Test
    void testLoadLookupEntries() throws IOException {
        // Setup
        Path lookupFile = tempDir.resolve("lookup.csv");
        Files.writeString(lookupFile,
                "dstport,protocol,tag\n" +
                        "80,tcp,web\n" +
                        "443,tcp,secure-web\n" +
                        "22,tcp,ssh"
        );

        // Execute
        List<LookupEntry> entries = loader.loadLookupEntries(lookupFile.toString());

        // Verify
        assertEquals(3, entries.size());
        assertEquals(new LookupEntry(80, "tcp", "web"), entries.get(0));
        assertEquals(new LookupEntry(443, "tcp", "secure-web"), entries.get(1));
        assertEquals(new LookupEntry(22, "tcp", "ssh"), entries.get(2));
    }

    @Test
    void testLoadLookupEntriesWithInvalidFile() {
        // Setup
        String nonExistentFile = "non-existent.csv";

        // Execute & Verify
        assertThrows(LookupTableLoadException.class, () -> loader.loadLookupEntries(nonExistentFile));
    }
}
