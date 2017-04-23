package bits.array;

/**
 * @author tomer
 * @since 4/21/17
 */
public interface BitArray {

    int size();

    byte[] toBytes();

    default BitArray cut(int newLength) {
        return cut(0, newLength);
    }

    BitArray cut(int from, int to);

    BitArray xor(BitArray array);
}
