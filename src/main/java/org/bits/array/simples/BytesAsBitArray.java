package org.bits.array.simples;

import org.bits.Bits;
import org.bits.array.AbstractBitArray;
import org.bits.array.BitArray;
import org.bits.array.operators.SliceBitArray;

/**
 * Light bit array without coping until needed.
 * @author tomer
 * @since 4/21/17
 */
public class BytesAsBitArray extends AbstractBitArray {

    /* --- Members --- */

    /** The array. */
    private final byte[] array;

    /** The size. */
    private final int size;

    /* --- Constructors --- */

    /**
     * All arguments constructor.
     * @param array The array of bits. Will be copied.
     * @param size The size of the array.
     */
    public BytesAsBitArray(byte[] array, int size) {
        this.array = Bits.copyOf(array, size);
        this.size = size;
    }

    /**
     * All bits are relevant.
     * @param array The array of bits.
     */
    public BytesAsBitArray(byte[] array) {
        this(array, array.length * Byte.SIZE);
    }

    /* --- BitArray Impl. --- */

    @Override
    public int size() {
        return size;
    }

    @Override
    public byte[] toBytes() {
        return Bits.copyOf(array, size);
    }

    @Override
    public BitArray cut(int from, int to) {
        return new SliceBitArray(this, from, to);
    }
}
