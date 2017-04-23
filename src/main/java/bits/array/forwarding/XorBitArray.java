package bits.array.forwarding;

import bits.array.BitArray;
import com.google.common.base.Preconditions;

/**
 * @author tomer
 * @since 4/21/17
 */
public class XorBitArray extends ForwardingBitArray {

    private final BitArray smallerBitArray;

    public XorBitArray(BitArray a, BitArray b) {
        super(b.size() > a.size() ? b : a);
        smallerBitArray = b.size() <= a.size() ? b : a;
    }

    @Override
    public byte[] toBytes() {
        byte[] biggerBytes = super.toBytes();
        byte[] smallerBytes = smallerBitArray.toBytes();
        for (int i = 0; i < smallerBytes.length; i++) {
            biggerBytes[i] ^= smallerBytes[i];
        }
        return biggerBytes;
    }
}
