package bits;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tomer
 * @since 4/21/17
 */
class BitsTest {

    /* --- Providers --- */

    static Stream<Arguments> masksAndSizesProvider() {
        return Stream.of(
                ObjectArrayArguments.create((byte) 0x0, 0),
                ObjectArrayArguments.create((byte) 0x1, 1),
                ObjectArrayArguments.create((byte) 0x3, 2),
                ObjectArrayArguments.create((byte) 0x7, 3),
                ObjectArrayArguments.create((byte) 0xF, 4),
                ObjectArrayArguments.create((byte) 0x1F, 5),
                ObjectArrayArguments.create((byte) 0x3F, 6),
                ObjectArrayArguments.create((byte) 0x7F, 7),
                ObjectArrayArguments.create((byte) 0xFF, 8)
        );
    }

    static Stream<Arguments> dataAndShiftAndExpectedProvider() {
        return Stream.of(
                ObjectArrayArguments.create(new byte[]{(byte) 0XFF, (byte)0xFF}, Byte.SIZE, new byte[]{(byte) 0XFF, (byte)0x0})
        );
    }

    /* --- Tests --- */

    @ParameterizedTest
    @MethodSource(names = "masksAndSizesProvider")
    void createMask(byte expectedMask, int size) {
        byte actualMask = Bits.createMask(size);
        assertEquals(expectedMask, actualMask);
    }

    @ParameterizedTest
    @MethodSource(names = "dataAndShiftAndExpectedProvider")
    void shiftLeft(byte[] data, int len, byte[] expectedData) {
        byte[] actualData = Bits.shiftLeft(data, len);

        assertArrayEquals(expectedData, actualData);
    }

}