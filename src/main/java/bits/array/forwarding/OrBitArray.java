package bits.array.forwarding;

import bits.array.BitArray;

/**
 * @author tomer
 * @since 4/21/17
 */
public class OrBitArray extends ForwardingTwoBitArray {

    public OrBitArray(BitArray a, BitArray b) {
        super(a, b);
    }

    @Override
    public byte[] toBytes() {
        byte[] biggerBytes = getBiggerBitArray().toBytes();
        byte[] smallerBytes = getSmallerBitArray().toBytes();
        for (int i = 0; i < smallerBytes.length; i++) {
            biggerBytes[i] |= smallerBytes[i];
        }
        return biggerBytes;
    }
}
