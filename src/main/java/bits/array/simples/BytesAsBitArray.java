package bits.array.simples;

import bits.Bits;
import bits.array.AbstractBitArray;
import com.google.common.base.Preconditions;

/**
 * Light bit array without coping until needed.
 * @author tomer
 * @since 4/21/17
 */
public class BytesAsBitArray extends AbstractBitArray {

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

    public BytesAsBitArray(byte[] array) {
        this(array, 0, array.length * Byte.SIZE);
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
    public BytesAsBitArray cut(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, size());
        return new BytesAsBitArray(array, startBit + from, startBit + to);
    }
}
