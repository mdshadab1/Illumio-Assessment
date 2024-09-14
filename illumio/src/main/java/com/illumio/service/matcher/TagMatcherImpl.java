package com.illumio.service.matcher;

import com.illumio.api.matcher.TagMatchStrategy;
import com.illumio.api.matcher.TagMatcher;
import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagMatcherImpl implements TagMatcher {
    private final TagMatchStrategy strategy;

    public TagMatcherImpl(TagMatchStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findMatchingTag(Optional<FlowLogEntry> entry, List<LookupEntry> lookupEntries) {
        return strategy.match(entry, lookupEntries);
    }
}
