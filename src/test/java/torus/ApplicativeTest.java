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
    class UserAPI {
        public static final String TEST_NAME = "Kim";
        public static final int TEST_AGE = 29;
        public static final String TEST_ADDRESS = "Seoul";
        public static final User SUCCESS_CASE = new User(TEST_NAME, TEST_AGE, TEST_ADDRESS);
        private static final int TEST_NAME_REQUEST_TIME = 1;
        private static final int TEST_AGE_REQUEST_TIME = 2;
        private static final int TEST_ADDRESS_REQUEST_TIME = 3;

        private static <A> Optional<A> validateX(A x, boolean willPass) {
            return willPass ? Optional.of(x) : Optional.empty();
        }

        private static <A> CompletableFuture<Optional<A>> requestX(A x, int requestTime, boolean willPassValidation) {
            return CompletableFuture.supplyAsync(() -> {
                wait(requestTime);
                return validateX(x, willPassValidation);
            });
        }

        private static void wait(int secondsToWait) {
            try {
                Thread.sleep(secondsToWait * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static Optional<String> getValidatedName(boolean willPass) {
            return validateX(TEST_NAME, willPass);
        }

        public static Optional<Integer> getValidatedAge(boolean willPass) {
            return validateX(TEST_AGE, willPass);
        }

        public static Optional<String> getValidatedAddress(boolean willPass) {
            return validateX(TEST_ADDRESS, willPass);
        }

        public static CompletableFuture<Optional<String>> requestValidatedUserName(boolean willPassValidation) {
            return requestX(TEST_NAME, TEST_NAME_REQUEST_TIME, willPassValidation);
        }

        public static CompletableFuture<Optional<Integer>> requestValidatedUserAge(boolean willPassValidation) {
            return requestX(TEST_AGE, TEST_AGE_REQUEST_TIME, willPassValidation);
        }

        public static CompletableFuture<Optional<String>> requestValidatedUserAddress(boolean willPassValidation) {
            return requestX(TEST_ADDRESS, TEST_ADDRESS_REQUEST_TIME, willPassValidation);
        }

        public static CompletableFuture<Optional<Integer>> requestValidatedUserAgeByName(String name, boolean willPassValidation) {
            return requestValidatedUserAge(willPassValidation);
        }

        public static CompletableFuture<Optional<String>> requestValidatedUserAddressByAge(int age, boolean willPassValidation) {
            return requestValidatedUserAddress(willPassValidation);
        }
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