package org.bits;

import com.google.common.collect.Streams;
import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Stream;

/**
 * This class is a utility class to handle bytes.
 *
 * Bits in array are looking like this:
 * | ------------ first byte ------------ | | ------------ second byte ------------ | ...
 *  most significant ... least significant   most significant ... least significant   ...
 *         0         ...       7                    8         ...       15            ...
 *
 * @author tomer
 * @since 4/21/17
 */
public final class Bytes {

    /* --- Constructors --- */

    /**
     * This class is a utility, so no constructors is needed.
     */
    private Bytes() {}

    /* --- Public Static Methods --- */

    public static byte[] xor(byte[] a, byte[] b) {
        return ArrayUtils.toPrimitive(Streams.zip(
                Stream.concat(Stream.of(ArrayUtils.toObject(a)), Stream.generate(() -> (byte) 0)),
                Stream.concat(Stream.of(ArrayUtils.toObject(b)), Stream.generate(() -> (byte) 0)),
                Bytes::xor
        ).toArray(Byte[]::new));
    }

    public static byte[] or(byte[] a, byte[] b) {
        return ArrayUtils.toPrimitive(Streams.zip(
                Stream.concat(Stream.of(ArrayUtils.toObject(a)), Stream.generate(() -> (byte) 0)),
                Stream.concat(Stream.of(ArrayUtils.toObject(b)), Stream.generate(() -> (byte) 0)),
                Bytes::or
        ).toArray(Byte[]::new));
    }

    public static byte[] and(byte[] a, byte[] b) {
        return ArrayUtils.toPrimitive(Streams.zip(
                Stream.concat(Stream.of(ArrayUtils.toObject(a)), Stream.generate(() -> (byte) 0)),
                Stream.concat(Stream.of(ArrayUtils.toObject(b)), Stream.generate(() -> (byte) 0)),
                Bytes::and
        ).toArray(Byte[]::new));
    }

    public static byte xor(byte a, byte b) {
        return (byte) (a ^ b);
    }

    public static byte or(byte a, byte b) {
        return (byte) (a | b);
    }

    public static byte and(byte a, byte b) {
        return (byte) (a & b);
    }
}
