package org.bits.array.operators;

import org.bits.Bytes;
import org.bits.array.BitArray;
import org.bits.array.forwarding.ForwardingTwoBitArray;

/**
 * Or two {@link BitArray}. The calculation will happen on access.
 * @author tomer
 * @since 4/21/17
 */
public class OrBitArray extends ForwardingTwoBitArray {

    public OrBitArray(BitArray a, BitArray b) {
        super(a, b, Bytes::or);
    }
}
