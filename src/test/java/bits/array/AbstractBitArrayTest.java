package bits.array;

import bits.array.forwarding.ForwardingBitArray;
import bits.array.forwarding.SliceBitArray;
import bits.array.forwarding.XorBitArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author tomer
 * @since 4/23/17
 */
class AbstractBitArrayTest {

    @Mock(answer = CALLS_REAL_METHODS)
    AbstractBitArray testedObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void cut() {
        int size = 5;
        when(testedObject.size()).thenReturn(size);
        assertThat(testedObject.cut(size - 1), instanceOf(SliceBitArray.class));
    }

    @Test
    void xor() {
        BitArray array = mock(BitArray.class);
        assertThat(testedObject.xor(array), instanceOf(XorBitArray.class));
    }

}