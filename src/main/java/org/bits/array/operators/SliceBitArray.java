package org.bits.array.operators;

import org.bits.Bits;
import org.bits.array.BitArray;
import com.google.common.base.Preconditions;
import org.bits.array.forwarding.ForwardingBitArray;

/**
 * Light bit array without coping until needed.
 * @author tomer
 * @since 4/21/17
 */
public class SliceBitArray extends ForwardingBitArray {

    /* --- Members --- */

    private final int startBit;

    private final int endBit;

    /* --- Constructors --- */

    public SliceBitArray(BitArray array, int startBit, int endBit) {
        super(array, (bytes) -> Bits.copyOfRange(bytes, startBit, endBit));
        Preconditions.checkPositionIndexes(startBit, endBit, array.size());
        this.startBit = startBit;
        this.endBit = endBit;
    }

    /* --- BitArray Impl. --- */

    @Override
    public int size() {
        return endBit - startBit;
    }

    @Override
    public BitArray cut(int from, int to) {
        return new SliceBitArray(forwarded(), startBit + from, startBit + to);
    }
}
