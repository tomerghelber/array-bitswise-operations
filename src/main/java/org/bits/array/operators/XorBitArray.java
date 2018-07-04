package org.bits.array.operators;

import org.bits.Bytes;
import org.bits.array.BitArray;
import org.bits.array.forwarding.ForwardingTwoBitArray;

/**
 * @author tomer
 * @since 4/21/17
 */
public class XorBitArray extends ForwardingTwoBitArray {

    public XorBitArray(BitArray a, BitArray b) {
        super(a, b, Bytes::or);
    }
}
