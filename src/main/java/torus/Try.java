package torus;

import java.util.Optional;

public class Try {
    public static <A> A get(FunctionThrowingCheckedException<A> f) {
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A getOrElse(FunctionThrowingCheckedException<A> f, A defaultValue) {
        try {
            return f.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <A> Optional<A> maybeGet(FunctionThrowingCheckedException<A> f) {
        try {
            return Optional.ofNullable(f.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

@FunctionalInterface
interface FunctionThrowingCheckedException<A> {
    A get() throws Exception;
}