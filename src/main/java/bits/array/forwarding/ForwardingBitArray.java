package bits.array.forwarding;

import bits.array.AbstractBitArray;
import bits.array.BitArray;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class ForwardingBitArray extends AbstractBitArray {

    /* --- Members --- */

    private final BitArray array;

    /* --- Constructors --- */

    public ForwardingBitArray(BitArray array) {
        this.array = array;
    }

     /* --- BitArray Impl. --- */

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public byte[] toBytes() {
        return array.toBytes();
    }

    /* --- Protected Methods --- */

    protected final BitArray forwarded() {
        return array;
    }
}
