package bits.array.forwarding;

import bits.array.BitArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author tomer
 * @since 4/23/17
 */
class ForwardingBitArrayTest {

    ForwardingBitArray testedObject;

    @Mock
    BitArray bitArrayMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        testedObject = new ForwardingBitArray(bitArrayMock) {};
    }

    @Test
    void size() {
        int size = 5;
        when(bitArrayMock.size()).thenReturn(size);

        assertEquals(size, testedObject.size());
        verify(bitArrayMock).size();
    }

    @Test
    void toBytes() {
        byte[] bytes = new byte[0];
        when(bitArrayMock.toBytes()).thenReturn(bytes);

        assertArrayEquals(bytes, testedObject.toBytes());
        verify(bitArrayMock).toBytes();
    }

    @Test
    void forwarded() {
        assertEquals(bitArrayMock, testedObject.forwarded());
    }

}