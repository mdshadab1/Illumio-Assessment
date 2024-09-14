package com.illumio.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LookupEntry {
    private int dstPort;
    private String protocol;
    private String tag;

    @Override
    public String toString() {
        return "LookupEntry{" +
                ", dstPort=" + dstPort +
                ", protocol=" + protocol +
                ", tag=" + tag +
                '}';
    }
}
