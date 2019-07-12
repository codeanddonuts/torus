package torus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ThisMapTest {
    Map<Integer, String> testMap;

    @BeforeEach
    void setUp() {
        testMap = new HashMap<>();
    }

    @Test
    void returnThisTest() {
        assertThat(
                ThisMap.put(testMap, 2, "apple")
                        .get(2)
        ).isEqualTo("apple");
    }

    @Test
    void chainingTest() {
        assertThat(
                ThisMap.beginChain(testMap)
                        .put(2, "apple")
                        .put(7, "banana")
                        .put(111, "kiwi")
                        .putAll(Arrays.asList(Pair.of(1, "elephant"), Pair.of(128, "tiger")))
                        .putAll(new HashMap<>() {{
                            put(89, "gold");
                            put(7727, "silver");
                            put(6125113, "platinum");
                        }})
                        .endChain()
                        .values().stream()
                        .map(String::length)
                        .sorted()
        ).isEqualTo(
                Arrays.asList(4, 4, 5, 5, 6, 6, 8, 8)
        );
    }
}