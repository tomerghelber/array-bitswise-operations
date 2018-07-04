package org.bits.array.forwarding;

import org.bits.array.AbstractBitArray;
import org.bits.array.BitArray;

import java.util.function.Function;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class ForwardingBitArray extends AbstractBitArray {

    /* --- Members --- */

    private final BitArray array;

    private final Function<byte[], byte[]> manipulator;

    /* --- Constructors --- */

    protected ForwardingBitArray(BitArray array) {
        this(array, Function.identity());
    }

    protected ForwardingBitArray(BitArray array, Function<byte[], byte[]> manipulator) {
        this.array = array;
        this.manipulator = manipulator;
    }

     /* --- BitArray Impl. --- */

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public byte[] toBytes() {
        return manipulator.apply(array.toBytes());
    }

    /* --- Protected Methods --- */

    protected final BitArray forwarded() {
        return array;
    }
}
