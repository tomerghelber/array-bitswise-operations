package bits.array.forwarding;

import bits.array.BitArray;
import com.google.common.base.Preconditions;

/**
 * @author tomer
 * @since 4/21/17
 */
public class XorBitArray extends ForwardingTwoBitArray {

    public XorBitArray(BitArray a, BitArray b) {
        super(a, b);
    }

    @Override
    public byte[] toBytes() {
        byte[] biggerBytes = getBiggerBitArray().toBytes();
        byte[] smallerBytes = getSmallerBitArray().toBytes();
        for (int i = 0; i < smallerBytes.length; i++) {
            biggerBytes[i] ^= smallerBytes[i];
        }
        return biggerBytes;
    }
}
