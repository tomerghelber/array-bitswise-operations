package bits;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Bits in array are looking like this:
 * | ------------ first byte ------------ | | ------------ second byte ------------ | ...
 *  most significant ... least significant   most significant ... least significant   ...
 *         0         ...       7                    8         ...       15            ...
 *
 * @author tomer
 * @since 4/21/17
 */
public final class Bits {

    /* --- Public static Members --- */

    private static final Pattern BITS_REGEX = Pattern.compile("(?<=\\G[01]{8})");

    public static final int BITS_IN_BYTE = 8;

    /* --- Constructors --- */

    /**
     * This class is a utility, so no constructors is needed.
     */
    private Bits() {}

    /* --- Public Static Methods --- */

    /**
     * Copying part of bits.
     * @param original The data to copy from.
     * @param newLength The new length of the data.
     * @return The copied data
     */
    public static byte[] copyOf(byte[] original, int newLength) {
        return copyOfRange(original, 0, newLength);
    }

    /**
     * Copying part og bits.
     * @param original The data to copy from.
     * @param from The start place of the new data.
     * @param to The end place of the new data.
     * @return The copied data.
     */
    public static byte[] copyOfRange(byte[] original, int from, int to) {
        Preconditions.checkPositionIndexes(from, to, original.length * Byte.SIZE);
        int newLength = to - from;
        int arraySize = (int) StrictMath.ceil((double) newLength / Byte.SIZE);
        byte mask = (byte) (0xFF - createMask(newLength % Byte.SIZE));
        byte[] shifted = shiftLeft(original, from);
        byte[] cut = Arrays.copyOf(shifted, arraySize);
        cut[cut.length - 1] &= mask;
        return cut;

    }

    /**
     * Shifts input byte array len bits left.
     * @param original The data to shift.
     * @param len The new length of the data.
     * @return The new data after the shift.
     */
    public static byte[] shiftLeft(byte[] original, int len) {
        Preconditions.checkPositionIndex(len, len);
        byte[] data = Arrays.copyOf(original, original.length);
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
     * @param original The data to shift.
     * @param len The new length of the data.
     * @return The new data after the shift.
     */
    public static byte[] shiftRight(byte[] original, int len) {
        Preconditions.checkPositionIndex(len, len);
        byte[] data = Arrays.copyOf(original, original.length);
        int shift = len % Byte.SIZE;
        byte carryMask = (byte) (0xFF - createMask(shift));
        int offset = len / Byte.SIZE;
        for (int i = data.length - 1; i >= 0; i--) {
            int srcIndex = i - offset;
            if (srcIndex < 0) {
                data[i] = 0;
            } else {
                byte src = data[srcIndex];
                byte dst = (byte) ((src & 0xFF) >>> shift);
                if (srcIndex > 0) {
                    dst |= data[srcIndex - 1] << (Byte.SIZE - shift) & carryMask;
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

    /**
     * Gets bytes - return the binary string of it
     * @param bytes The data to convert.
     * @return The represent string.
     */
    public static String toBinaryString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes) {
            stringBuilder.append(toBinaryString(b));
        }
        return stringBuilder.toString();
    }

    /**
     * Gets byte - return the binary string of it.
     * @param b The byte to represent.
     * @return The represent string.
     */
    public static String toBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    /**
     * Gets string - return the byte it represents.
     * @param binary The represent string.
     * @return The byte.
     */
    public static byte toByte(String binary) {
        return (byte) Integer.parseUnsignedInt(binary, 2);
    }

    /**
     * Gets a string and return the bytes it represents.
     * @param binaryData The represent string
     * @return The bytes.
     */
    public static byte[] toBytes(String binaryData) {
        String[] binariesData = BITS_REGEX.split(binaryData);
        byte[] bytes = new byte[binariesData.length];
        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = toByte(binariesData[i]);
        }
        return bytes;
    }
}
