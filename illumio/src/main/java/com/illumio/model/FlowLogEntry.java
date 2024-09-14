package com.illumio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FlowLogEntry {
    private int version;
    private String accountId;
    private String interfaceId;
    private String srcAddress;
    private String dstAddress;
    private int srcPort;
    private int dstPort;
    private int protocol;
    private long packets;
    private long bytes;
    private long startTime;
    private long endTime;
    private String action;
    private String logStatus;

    @Override
    public String toString() {
        return "FlowLogEntry{" +
                "version=" + version +
                ", accountId='" + accountId +
                ", interfaceId='" + interfaceId +
                ", srcAddr='" + srcAddress +
                ", dstAddr='" + dstAddress +
                ", srcPort=" + srcPort +
                ", dstPort=" + dstPort +
                ", protocol=" + protocol +
                ", packets=" + packets +
                ", bytes=" + bytes +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", action='" + action +
                ", logStatus='" + logStatus +
                '}';
    }
}

