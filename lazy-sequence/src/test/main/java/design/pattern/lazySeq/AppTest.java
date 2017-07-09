package design.pattern.lazySeq;

import org.junit.Test;
import org.sandcastle.apps.LazySeq;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void shouldGenerateFibonacciSequence() throws Exception {
        final LazySeq<Integer> fibonacciSeries = App.lastTwoFib(0, 1);

        assertTrue(fibonacciSeries.startsWith(asList(0, 1, 1, 2, 3, 5, 8, 13, 21)));
    }

    @Test
    public void shouldGenerateAndLastTenDigitsFromFibonacciSequence() throws Exception {
        final LazySeq<Integer> fib = LazySeq.iterate(2, n -> n + 1);
        List<Integer> skipFirstFiveFibSequencedNumbers = fib.drop(5).take(10).toList();

        assertThat(skipFirstFiveFibSequencedNumbers, containsInAnyOrder(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
    }

    @Test
    public void shouldCreateGroupsOfThreeCharacters() throws Exception {
        final LazySeq<Character> letters = LazySeq.of('A', 'B', 'C', 'D', 'E', 'F', 'G');
        LazySeq<List<Character>> letterList = letters.grouped(3).force();

        // [ [ABC], [DEF], [G] ]
        assertTrue(letterList.size() == 3);
    }
}