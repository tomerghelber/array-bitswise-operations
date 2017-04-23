package bits.array.forwarding;

import bits.Bits;
import bits.array.BitArray;
import com.google.common.base.Preconditions;

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
        super(array);
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
    public byte[] toBytes() {
        return Bits.copyOfRange(super.toBytes(), startBit, endBit);
    }

    @Override
    public BitArray cut(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, size());
        return new SliceBitArray(forwarded(), startBit + from, startBit + to);
    }
}
