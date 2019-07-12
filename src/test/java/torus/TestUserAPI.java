package torus;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class TestUserAPI {
    public static final String TEST_NAME = "Kim";
    public static final int TEST_AGE = 29;
    public static final String TEST_ADDRESS = "Seoul";
    public static final TestUser SUCCESS_CASE = new TestUser(TEST_NAME, TEST_AGE, TEST_ADDRESS);
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
