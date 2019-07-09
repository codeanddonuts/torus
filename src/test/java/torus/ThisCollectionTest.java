package torus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ThisCollectionTest {
    Set<Integer> testSet;

    @BeforeEach
    void setUp() {
        testSet = new HashSet<>();
    }

    @Test
    void equalityTest() {
        assertThat(
                testSet == ThisCollection.addAll(testSet, Arrays.asList(1, 2, 3))
                && testSet == ThisCollection.removeIf(testSet, x -> x == 2)
        ).isTrue();
    }

    @Test
    void chainingTest() {
        assertThat(
                ThisCollection.addAll(testSet, Arrays.asList(2, 3, 5, 7))
                                .stream().map(x -> x * 2)
                                .collect(Collectors.toSet())
        ).isEqualTo(
                new HashSet<Integer>() {{
                    add(4);
                    add(6);
                    add(10);
                    add(14);
                }}
        );
    }
}