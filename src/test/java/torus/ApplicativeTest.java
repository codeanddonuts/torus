package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicativeTest {
    @Test
    void applyListTest() {
        assertThat(
                Applicative.apply(
                        Stream.of(x -> x + 1, x -> x * 2),
                        Stream.of(1, 2, 3, 4, 5)
                )
        ).isEqualTo(
                Arrays.asList(2, 2, 3, 4, 4, 6, 5, 8, 6, 10)
        );
    }

    @Test
    void applyJustTest() {
        assertThat(
                Applicative.apply(
                        Optional.of(x -> x * 5),
                        Optional.of(7)
                )
        ).isEqualTo(
                Optional.of(35)
        );
    }

    @Test
    void applyNothingTest() {
        final Optional<Integer> nothing = Optional.empty();
        assertThat(
                Applicative.apply(
                        Optional.of(x -> x + 1),
                        nothing
                )
        ).isEqualTo(
                Optional.empty()
        );
    }

    @Test
    void applyOptionalChainTest() {
        assertThat(
                Applicative.apply(
                        Applicative.apply(
                                Applicative.apply(
                                        Optional.of(x -> y -> z -> x + y + z),
                                        Optional.of(7)
                                ),
                                Optional.of(11)
                        ),
                        Optional.of(19)
                )
        ).isEqualTo(
                Optional.of(37)
        );
    }

    @Test
    void applyAllOptionalsTest() {
        Function<Integer, Function<Integer, Function<Integer, Integer>>> f = x -> y -> z -> x * y * z;
        assertThat(
                Applicative.apply(
                        Optional.of(f),
                        Optional.of(3),
                        Optional.of(5),
                        Optional.of(7)
                )
        ).isEqualTo(
                Optional.of(105)
        );
    }

    @Test
    void applyAllOptionalsPartialApplicationTest() {
        Function<Integer, Function<Integer, Function<Integer, Function<Integer, Integer>>>> f
                = w -> x -> y -> z -> w * x * y * z;
        assertThat(
                Applicative.apply(
                        Optional.of(f),
                        Optional.of(3),
                        Optional.of(5),
                        Optional.of(7)
                ).map(g -> g.apply(9))
        ).isEqualTo(
                Optional.of(3 * 5 * 7 * 9)
        );
    }

    @Test
    void userInitTestMaybeMonadic() {
        assertThat(
                UserAPI.getUserName(true).join().flatMap(name ->
                        UserAPI.getUserAge(true).join().flatMap(age ->
                                UserAPI.getUserAddress(true).join().map(address ->
                                        new User(name, age, address)
                                )
                        )
                )
        ).isEqualTo(
                Optional.of(new User("Kim", 29, "Seoul"))
        );
    }

    @Test
    void userInitTestApplicativeLiftMaybeSuccess() {
        assertThat(
                Applicative.lift(
                        Curry.convert(User::new),
                        UserAPI.getUserName(true).join(),
                        UserAPI.getUserAge(true).join(),
                        UserAPI.getUserAddress(true).join()
                )
        ).isEqualTo(
                Optional.of(new User("Kim", 29, "Seoul"))
        );
    }

    @Test
    void userInitTestApplicativeLiftTupleOfMaybeSuccess() {
        assertThat(
                Applicative.lift(
                        Curry.convert(User::new),
                        new Triplet<>(
                                UserAPI.getUserName(true).join(),
                                UserAPI.getUserAge(true).join(),
                                UserAPI.getUserAddress(true).join()
                        )
                )
        ).isEqualTo(
                Optional.of(new User("Kim", 29, "Seoul"))
        );
    }

    @Test
    void userInitTestFutureOfMaybeApplicativeFailure() {
        assertThat(
                Applicative.lift(
                        Curry.convert(User::new),
                        Stream.of(
                                UserAPI.getUserName(true),
                                UserAPI.getUserAge(false),
                                UserAPI.getUserAddress(true)
                        ).map(CompletableFuture::join)
                )
        ).isEqualTo(
                Optional.empty()
        );
    }

    @Test
    void userInitTestFactoryMethod() {
        assertThat(
                User.of("Kim", 29, "Seoul")
        ).isEqualTo(
                Optional.of(new User("Kim", 29, "Seoul"))
        );
    }
}

class User {
    private final String name;
    private final int age;
    private final String address;

    public static Optional<User> of(String name, int age, String address) {
        return Applicative.lift(
                Curry.convert(User::new),
                validateName(name),
                validateAge(age),
                validateAddress(address)
        );
    }

    private static Optional<String> validateName(String name) {
        return Optional.ofNullable(name).filter(x -> x.length() > 2);
    }

    private static Optional<Integer> validateAge(int age) {
        return Optional.of(age).filter(x -> x > 19);
    }

    private static Optional<String> validateAddress(String address) {
        return Optional.ofNullable(address).filter(x -> x.length() > 4);
    }

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User rhs = (User) o;
        return (this.age == rhs.age)
                && (this.name.equals(rhs.name))
                && (this.address.equals(rhs.address));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

class UserAPI {
    public static CompletableFuture<Optional<String>> getUserName(boolean success) {
        return CompletableFuture.completedFuture(success ? Optional.of("Kim") : Optional.empty());
    }

    public static CompletableFuture<Optional<Integer>> getUserAge(boolean success) {
        return CompletableFuture.completedFuture(success ? Optional.of(29) : Optional.empty());
    }

    public static CompletableFuture<Optional<String>> getUserAddress(boolean success) {
        return CompletableFuture.completedFuture(success ? Optional.of("Seoul") : Optional.empty());
    }
}