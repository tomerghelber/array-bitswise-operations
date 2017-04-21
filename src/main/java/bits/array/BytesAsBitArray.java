package bits.array;

import bits.Bits;
import com.google.common.base.Preconditions;

/**
 * @author tomer
 * @since 4/21/17
 */
public class BytesAsBitArray implements BitArray {

    /* --- Members --- */

    private final byte[] array;

    private final int startBit;

    private final int endBit;

    /* --- Constructors --- */

    public BytesAsBitArray(byte[] array, int startBit, int endBit) {
        Preconditions.checkPositionIndexes(startBit, endBit, array.length * Byte.SIZE);
        this.array = array;
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
        return Bits.copyOfRange(array, startBit, endBit);
    }

    @Override
    public BitArray cut(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, size());
        return new BytesAsBitArray(array, startBit + from, startBit + to);
    }
}
