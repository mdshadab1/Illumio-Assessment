package com.illumio.service.matcher;

import com.illumio.api.matcher.TagMatchStrategy;
import com.illumio.model.FlowLogEntry;
import com.illumio.model.LookupEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultTagMatchStrategy implements TagMatchStrategy {

    @Value("${app.untagged.label:Untagged}")
    private String untaggedLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public String match(Optional<FlowLogEntry> optionalEntry, List<LookupEntry> lookupEntries) {
        if (!optionalEntry.isPresent()) {
            return untaggedLabel;
        }

        FlowLogEntry entry = optionalEntry.get();
        String protocol = protocolToString(entry.getProtocol());

        for (LookupEntry lookupEntry : lookupEntries) {
            if (lookupEntry.getDstPort() == entry.getDstPort() &&
                    lookupEntry.getProtocol().equalsIgnoreCase(protocol)) {
                return lookupEntry.getTag();
            }
        }

        return untaggedLabel;
    }

    /**
     * Converts a protocol number to its string representation.
     *
     * @param protocol The protocol number.
     * @return The string representation of the protocol.
     */
    private String protocolToString(int protocol) {
        switch (protocol) {
            case 6:
                return "tcp";
            case 17:
                return "udp";
            case 1:
                return "icmp";
            default:
                return "unknown";
        }
    }
}
