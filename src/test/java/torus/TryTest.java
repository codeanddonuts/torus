package torus;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class TryTest {
    @Test
    void getTest() {
        try {
            Try.get(() -> new String(Files.readAllBytes(Paths.get(""))));
        } catch (RuntimeException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Test
    void getOrElseTest() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    void maybeGetTest() {
    }
}