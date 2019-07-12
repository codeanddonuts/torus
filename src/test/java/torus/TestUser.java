package torus;

import java.util.Objects;
import java.util.Optional;

class TestUser {
    private static final int MIN_AGE_OF_ADULT = 19;

    private final String name;
    private final int age;
    private final String address;

    public static Optional<TestUser> of(String name, int age, String address) {
        return Applicative.lift(
                Curry.convert(TestUser::new),
                validateIfEmpty(name),
                validateIfAdult(age),
                validateIfEmpty(address)
        );
    }

    private static Optional<String> validateIfEmpty(String input) {
        return Optional.ofNullable(input).filter(x -> x.length() > 0);
    }

    private static Optional<Integer> validateIfAdult(int age) {
        return Optional.of(age).filter(x -> x > MIN_AGE_OF_ADULT);
    }

    public TestUser(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestUser)) {
            return false;
        }
        TestUser rhs = (TestUser) o;
        return (this.age == rhs.age)
                && (this.name.equals(rhs.name))
                && (this.address.equals(rhs.address));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.age);
    }
}
