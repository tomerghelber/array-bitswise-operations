package bits;

/**
 * @author tomer
 * @since 4/21/17
 */
public final class Bits {

    /* --- Constructors --- */

    /**
     * This class is a utility, so no constructors is needed.
     */
    private Bits() {}

    /* --- Public Static Methods --- */

    /**
     * Shifts input byte array len bits left.
     * @param data The data to shift.
     * @param len The new length of the data.
     * @return The new data after the shift.
     * @apiNote This method will alter the input byte array.
     */
    public static byte[] shiftLeft(byte[] data, int len) {
        int shift = len % Byte.SIZE;
        byte carry_mask = createMask(shift);
        int offset = len / Byte.SIZE;
        for (int i = 0; i < data.length; i++) {
            int src_index = i + offset;
            if (src_index >= data.length) {
                data[i] = 0;
            } else {
                byte src = data[src_index];
                byte dst = (byte) (src << shift);
                if (src_index + 1 < data.length) {
                    dst |= data[src_index + 1] >>> (Byte.SIZE - shift) & carry_mask;
                }
                data[i] = dst;
            }
        }
        return data;
    }

    /**
     * Creates a mask for a byte.
     * @param length The length of the mask.
     * @return The mask.
     */
    public static byte createMask(int length) {
        return (byte) ((1 << length) - 1);
    }
}
