package org.bits.array.simples;

import org.bits.array.BitArray;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tomer
 * @since 4/21/17
 */
class BytesAsBitArrayTest {

    static Stream<Arguments> sizeProvider() {
        byte[] bytes = new byte[]{0x1, 0x8};
        BytesAsBitArray array = new BytesAsBitArray(bytes);
        BitArray cutArray = array.cut(7, 9);
        return Stream.of(
                Arguments.of(array, 16),
                Arguments.of(cutArray, 2)
        );
    }

    static Stream<Arguments> bytesProvider() {
        byte[] bytes = new byte[]{0x1, (byte)0x80};
        BytesAsBitArray array = new BytesAsBitArray(bytes);
        BitArray cutArray = array.cut(7, 9);
        return Stream.of(
                Arguments.of(array, bytes),
                Arguments.of(cutArray, new byte[]{(byte)0xC0})
        );
    }

    /* --- Tests --- */

    @ParameterizedTest
    @MethodSource(value = "sizeProvider")
    void size(BytesAsBitArray array, int expectedSize) {
        assertEquals(expectedSize, array.size());
    }

    @ParameterizedTest
    @MethodSource(value = "bytesProvider")
    void toBytes(BytesAsBitArray array, byte[] expectedBytes) {
        assertArrayEquals(expectedBytes, array.toBytes());
    }
}