package bits.array;

import bits.array.forwarding.AndBitArray;
import bits.array.forwarding.OrBitArray;
import bits.array.forwarding.SliceBitArray;
import bits.array.forwarding.XorBitArray;

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
