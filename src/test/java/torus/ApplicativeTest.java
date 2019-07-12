package torus;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
    void applyOptionalsChainTest() {
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
        assertThat(
                Applicative.apply(
                        Optional.of(x -> y -> z -> x * y * z),
                        Optional.of(3),
                        Optional.of(5),
                        Optional.of(7)
                )
        ).isEqualTo(
                Optional.of(105)
        );
    }

    @Test
    void userInitApplicativeLiftFactoryMethodTest() {
        assertThat(
                TestUser.of("Park", 21, "Incheon")
        ).isEqualTo(
                Optional.of(new TestUser("Park", 21, "Incheon"))
        );
    }

    @Test
    void userInitWithAPIMonadicBindMaybesTest() {
        assertThat(
                TestUserAPI.getValidatedName(true).flatMap(name ->
                        TestUserAPI.getValidatedAge(true).flatMap(age ->
                                TestUserAPI.getValidatedAddress(true).map(address ->
                                        new TestUser(name, age, address)
                                )
                        )
                )
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }

    @Test
    void userInitWithAPIApplicativeLiftMaybesTest() {
        assertThat(
                Applicative.lift(
                        Curry.convert(TestUser::new),
                        TestUserAPI.getValidatedName(true),
                        TestUserAPI.getValidatedAge(true),
                        TestUserAPI.getValidatedAddress(true)
                )
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }

    @Test
    void userInitWithAPIDoubleMonadicBindIndependentFuturesOfMaybeTest() {
        CompletableFuture<Optional<String>> futureOfMaybeName = TestUserAPI.requestValidatedUserName(true);
        CompletableFuture<Optional<Integer>> futureOfMaybeAge = TestUserAPI.requestValidatedUserAge(true);
        CompletableFuture<Optional<String>> futureOfMaybeAddress = TestUserAPI.requestValidatedUserAddress(true);
        assertThat(
                futureOfMaybeName.join().flatMap(name ->
                        futureOfMaybeAge.join().flatMap(age ->
                                futureOfMaybeAddress.join().map(address ->
                                        new TestUser(name, age, address)
                                )
                        )
                )
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }

    @Test
    void userInitWithAPIDoubleApplicativeLiftIndependentFuturesOfMaybeTest() {
        assertThat(
                Applicative.lift(
                        name -> age -> address -> Applicative.lift(Curry.convert(TestUser::new), name, age, address),
                        TestUserAPI.requestValidatedUserName(true),
                        TestUserAPI.requestValidatedUserAge(true),
                        TestUserAPI.requestValidatedUserAddress(true)
                ).join()
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }

    @Test
    void userInitWithAPIDoubleMonadicBindDependentFuturesOfMaybeTest() {
        var futureOfMaybeUser = TestUserAPI.requestValidatedUserName(true).thenCompose(maybeName ->
                maybeName.map(name -> TestUserAPI.requestValidatedUserAgeByName(name, true).thenCompose(maybeAge ->
                        maybeAge.map(age -> TestUserAPI.requestValidatedUserAddressByAge(age, true).thenApply(maybeAddress ->
                                maybeAddress.map(address ->
                                        new TestUser(name, age, address)
                                )
                        )).get()
                )).get()
        );
        assertThat(
                futureOfMaybeUser.join()
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }

    @Test
    void userInitWithAPIApplicativeLiftTupleOfMaybesTest() {
        assertThat(
                Applicative.lift(
                        Curry.convert(TestUser::new),
                        new Triplet<>(
                                TestUserAPI.getValidatedName(true),
                                TestUserAPI.getValidatedAge(true),
                                TestUserAPI.getValidatedAddress(true)
                        )
                )
        ).isEqualTo(
                Optional.of(TestUserAPI.SUCCESS_CASE)
        );
    }
}