package org.bits.array.forwarding;

import org.bits.array.AbstractBitArray;
import org.bits.array.BitArray;

import java.util.function.BiFunction;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class ForwardingTwoBitArray extends AbstractBitArray {


    /* --- Members --- */

    private final BitArray biggerBitArray;

    private final BitArray smallerBitArray;
    private final BiFunction<byte[], byte[], byte[]> maniplutate;

    /* --- Constructors --- */

    protected ForwardingTwoBitArray(BitArray a, BitArray b, BiFunction<byte[], byte[], byte[]> maniplutate) {
        if (b.size() < a.size()) {
            smallerBitArray = b;
            biggerBitArray = a;
        } else {
            smallerBitArray = a;
            biggerBitArray = b;
        }
        this.maniplutate = maniplutate;
    }

    /* --- BitArray Impl. --- */

    @Override
    public int size() {
        return biggerBitArray.size();
    }

    @Override
    public byte[] toBytes() {
        return maniplutate.apply(getBiggerBitArray().toBytes(), getSmallerBitArray().toBytes());
    }

    /* --- Protected Methods --- */

    protected BitArray getBiggerBitArray() {
        return biggerBitArray;
    }

    protected BitArray getSmallerBitArray() {
        return smallerBitArray;
    }
}
