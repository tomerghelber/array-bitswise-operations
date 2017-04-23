package bits.array.simples;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tomer
 * @since 4/21/17
 */
class BytesAsBitArrayTest {

    static Stream<Arguments> sizeProvider() {
        byte[] bytes = new byte[]{0x1, 0x8};
        BytesAsBitArray array = new BytesAsBitArray(bytes);
        BytesAsBitArray cutArray = array.cut(7, 9);
        return Stream.of(
                ObjectArrayArguments.create(array, 16),
                ObjectArrayArguments.create(cutArray, 2)
        );
    }

    static Stream<Arguments> bytesProvider() {
        byte[] bytes = new byte[]{0x1, (byte)0x80};
        BytesAsBitArray array = new BytesAsBitArray(bytes);
        BytesAsBitArray cutArray = array.cut(7, 9);
        return Stream.of(
                ObjectArrayArguments.create(array, bytes),
                ObjectArrayArguments.create(cutArray, new byte[]{(byte)0xC0})
        );
    }

    /* --- Tests --- */

    @ParameterizedTest
    @MethodSource(names = "sizeProvider")
    void size(BytesAsBitArray array, int expectedSize) {
        assertEquals(expectedSize, array.size());
    }

    @ParameterizedTest
    @MethodSource(names = "bytesProvider")
    void toBytes(BytesAsBitArray array, byte[] expectedBytes) {
        assertArrayEquals(expectedBytes, array.toBytes());
    }
}