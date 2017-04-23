package bits.array;

import bits.array.forwarding.SliceBitArray;
import bits.array.forwarding.XorBitArray;
import com.google.common.base.Preconditions;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class AbstractBitArray implements BitArray {

     /* --- BitArray Impl. --- */

    @Override
    public BitArray cut(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, size());

        return new SliceBitArray(this, from, to);
    }

    @Override
    public BitArray xor(BitArray array) {
        return new XorBitArray(this, array);
    }
}
