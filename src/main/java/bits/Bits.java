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
        byte carryMask = createMask(shift);
        int offset = len / Byte.SIZE;
        for (int i = 0; i < data.length; i++) {
            int srcIndex = i + offset;
            if (srcIndex >= data.length) {
                data[i] = 0;
            } else {
                byte src = data[srcIndex];
                byte dst = (byte) (src << shift);
                if (srcIndex + 1 < data.length) {
                    dst |= data[srcIndex + 1] >>> (Byte.SIZE - shift) & carryMask;
                }
                data[i] = dst;
            }
        }
        return data;
    }

    /**
     * Shifts input byte array len bits right.
     * @param data The data to shift.
     * @param len The new length of the data.
     * @return The new data after the shift.
     * @apiNote This method will alter the input byte array.
     */
    public static byte[] shiftRight(byte[] data, int len) {
        int shift = len % Byte.SIZE;
        byte carryMask = (byte) (Byte.MIN_VALUE - createMask(shift));
        int offset = len / Byte.SIZE;
        for (int i = data.length - 1; i >= 0; i--) {
            int srcIndex = i - offset;
            if (srcIndex < 0) {
                data[i] = 0;
            } else {
                byte src = data[srcIndex];
                byte dst = (byte) (src >> shift);
                if (srcIndex + 1 < data.length) {
                    dst |= data[srcIndex + 1] << (Byte.SIZE - shift) & carryMask;
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
