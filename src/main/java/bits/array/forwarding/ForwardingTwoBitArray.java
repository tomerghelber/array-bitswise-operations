package bits.array.forwarding;

import bits.array.BitArray;

/**
 * @author tomer
 * @since 4/21/17
 */
public abstract class ForwardingTwoBitArray extends ForwardingBitArray {

    private final BitArray smallerBitArray;

    public ForwardingTwoBitArray(BitArray a, BitArray b) {
        super(b.size() > a.size() ? b : a);
        smallerBitArray = b.size() <= a.size() ? b : a;
    }

    /* --- Protected Methods --- */

    protected BitArray getBiggerBitArray() {
        return forwarded();
    }

    protected BitArray getSmallerBitArray() {
        return smallerBitArray;
    }
}
