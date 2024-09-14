package com.illumio;

import com.illumio.api.matcher.TagMatchStrategy;
import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;
import com.illumio.service.matcher.TagMatcherImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagMatcherImplTest {

    @Mock
    private TagMatchStrategy strategy;

    private TagMatcherImpl tagMatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tagMatcher = new TagMatcherImpl(strategy);
    }

    @Test
    void testFindMatchingTag() {
        // Setup
        FlowLogEntry entry = new FlowLogEntry(2, "123", "eni-1", "10.0.0.1", "192.168.0.1", 12345, 80, 6, 100, 1000L, 1620000000L, 1620000060L, "ACCEPT", "OK");
        List<LookupEntry> lookupEntries = Arrays.asList(new LookupEntry(80, "tcp", "web"));
        when(strategy.match(Optional.of(entry), lookupEntries)).thenReturn("web");

        // Execute
        String result = tagMatcher.findMatchingTag(Optional.of(entry), lookupEntries);

        // Verify
        assertEquals("web", result);
        verify(strategy).match(Optional.of(entry), lookupEntries);
    }
}
