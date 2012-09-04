package net.purevirtual.scrapboard;

import java.util.BitSet;

public class Sample {
    private final short[] raw;
    private final short[] decoded;
    private final BitSet finished;
    private final String fileName;

    public Sample(short[] raw, String fileName) {
        this.raw = raw;
        this.decoded = new short[raw.length];
        this.finished = new BitSet(raw.length);
        this.fileName = fileName;
    }

    public short[] getDecoded() {
        return decoded;
    }

    public BitSet getFinished() {
        return finished;
    }

    public short[] getRaw() {
        return raw;
    }

    public String getFileName() {
        return fileName;
    }
    
    
}
