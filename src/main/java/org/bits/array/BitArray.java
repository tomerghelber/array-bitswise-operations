package org.bits.array;

/**
 * Array of bits. All the value are in bit's size.
 * @author tomer
 * @since 4/21/17
 */
public interface BitArray {

    /**
     * @return The size of the array in bits.
     */
    int size();

    /**
     * The bytes which contains the bits.
     * If the size is not dividable by 8, then the last byte will contain unknown bits.
     * @return The bytes which contains the bits.
     */
    byte[] toBytes();

    /**
     *
     * @param from The start index of the cut.
     * @param to The end index of the cut.
     * @return The cut array.
     */
    BitArray cut(int from, int to);

    /**
     * @param array The array to xor with.
     * @return The xor array.
     */
    BitArray xor(BitArray array);


    /**
     * @param array The array to or with.
     * @return The or array.
     */
    BitArray or(BitArray array);


    /**
     * @param array The array to and with.
     * @return The and array.
     */
    BitArray and(BitArray array);
}
