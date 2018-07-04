package org.bits;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

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
                Arguments.of((byte) 0x0, 0),
                Arguments.of((byte) 0x1, 1),
                Arguments.of((byte) 0x3, 2),
                Arguments.of((byte) 0x7, 3),
                Arguments.of((byte) 0xF, 4),
                Arguments.of((byte) 0x1F, 5),
                Arguments.of((byte) 0x3F, 6),
                Arguments.of((byte) 0x7F, 7),
                Arguments.of((byte) 0xFF, 8)
        );
    }

    static Stream<Arguments> dataAndShiftLeftAndExpectedProvider() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 0X0F}, 4, new byte[]{(byte) 0XF0}),
                Arguments.of(new byte[]{(byte) 0XF1, (byte) 0X0F}, 4, new byte[]{ (byte) 0X10, (byte) 0XF0}),
                Arguments.of(new byte[]{(byte) 0XFF, (byte)0xFF}, Byte.SIZE, new byte[]{(byte) 0XFF, (byte)0x0})
        );
    }

    static Stream<Arguments> dataAndShiftRightAndExpectedProvider() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 0XF0}, 4, new byte[]{(byte) 0X0F}),
                Arguments.of(new byte[]{(byte) 0XF1, (byte) 0XF0}, 4, new byte[]{ (byte) 0X0F, (byte) 0X1F}),
                Arguments.of(new byte[]{(byte) 0XFF, (byte)0xFF}, Byte.SIZE, new byte[]{(byte)0x0, (byte) 0XFF})
        );
    }

    static Stream<Arguments> dataAndToStringProvider() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 0x18}, "00011000"),
                Arguments.of(new byte[]{(byte) 0x01, (byte) 0x80}, "0000000110000000")
        );
    }

    static Stream<Arguments> byteAndToStringProvider() {
        return Stream.of(
                Arguments.of((byte) 0x18, "00011000"),
                Arguments.of((byte) 0x81, "10000001")
        );
    }

    /* --- Tests --- */

    @ParameterizedTest
    @MethodSource(value = "masksAndSizesProvider")
    void createMask(byte expectedMask, int size) {
        byte actualMask = Bits.createMask(size);
        assertEquals(expectedMask, actualMask);
    }

    @ParameterizedTest
    @MethodSource(value = "dataAndShiftLeftAndExpectedProvider")
    void shiftLeft(byte[] data, int len, byte[] expectedData) {
        byte[] actualData = Bits.shiftLeft(data, len);

        assertArrayEquals(expectedData, actualData, Bits.toBinaryString(expectedData) + "<->" + Bits.toBinaryString(actualData));
    }

    @ParameterizedTest
    @MethodSource(value = "dataAndShiftRightAndExpectedProvider")
    void shiftRight(byte[] data, int len, byte[] expectedData) {
        byte[] actualData = Bits.shiftRight(data, len);

        assertArrayEquals(expectedData, actualData, Bits.toBinaryString(expectedData) + "<->" + Bits.toBinaryString(actualData));
    }

    @ParameterizedTest
    @MethodSource(value = "dataAndToStringProvider")
    void toBinaryString(byte[] data, String expectedToString) {
        String actualToString = Bits.toBinaryString(data);
        assertEquals(expectedToString, actualToString);
    }

    @ParameterizedTest
    @MethodSource(value = "byteAndToStringProvider")
    void toBinaryString(byte data, String expectedToString) {
        String actualToString = Bits.toBinaryString(data);
        assertEquals(expectedToString, actualToString);
    }

    @ParameterizedTest
    @MethodSource(value = "dataAndToStringProvider")
    void toByteArray(byte[] expecteBytes, String data) {
        byte[] actualBytes = Bits.toBytes(data);
        assertArrayEquals(expecteBytes, actualBytes);
    }

    @ParameterizedTest
    @MethodSource(value = "byteAndToStringProvider")
    void toByte(byte expectedByte, String data) {
        byte actualByte = Bits.toByte(data);
        assertEquals(expectedByte, actualByte);
    }

    @Test
    void copyOf() {
        byte[] original = new byte[]{(byte) 0x01, (byte) 0x80};
        byte[] expected = new byte[]{(byte) 0x00};
        byte[] actual = Bits.copyOf(original, 7);

        assertArrayEquals(expected, actual, Bits.toBinaryString(expected) + "<->" + Bits.toBinaryString(actual));
    }

    @Test
    void copyOfRange() {
        byte[] original = new byte[]{(byte) 0x01, (byte) 0x80};
        byte[] expected = new byte[]{(byte) 0xC0};
        byte[] actual = Bits.copyOfRange(original, 7, 9);

        assertArrayEquals(expected, actual, Bits.toBinaryString(expected) + "<->" + Bits.toBinaryString(actual));
    }
}