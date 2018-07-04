package org.bits.array;

import org.bits.array.operators.AndBitArray;
import org.bits.array.operators.OrBitArray;
import org.bits.array.operators.SliceBitArray;
import org.bits.array.operators.XorBitArray;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class AbstractBitArray implements BitArray {

     /* --- BitArray Impl. --- */

    @Override
    public BitArray cut(int from, int to) {
        return new SliceBitArray(this, from, to);
    }

    @Override
    public BitArray xor(BitArray array) {
        return new XorBitArray(this, array);
    }

    @Override
    public BitArray or(BitArray array) {
        return new OrBitArray(this, array);
    }

    @Override
    public BitArray and(BitArray array) {
        return new AndBitArray(this, array);
    }
}
