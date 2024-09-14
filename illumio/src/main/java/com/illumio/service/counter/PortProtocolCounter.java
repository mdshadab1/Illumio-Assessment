package com.illumio.service.counter;

import com.illumio.api.counter.Counter;
import com.illumio.model.FlowLogEntry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PortProtocolCounter implements Counter {

    /**
     * {@inheritDoc}
     */
    public Map<String, Integer> count(List<FlowLogEntry> entries) {
        Map<String, Integer> counts = new HashMap<>();
        for (FlowLogEntry entry : entries) {
            String key = entry.getDstPort() + "," + protocolToString(entry.getProtocol());
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }
        return counts;
    }

    private String protocolToString(int protocol) {
        switch (protocol) {
            case 6: return "tcp";
            case 17: return "udp";
            case 1: return "icmp";
            default: return "unknown";
        }
    }
}