package torus;

import java.util.Optional;

public class Try {
    public static <A> A get(FunctionThrowingCheckedExceptions<A> f) {
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A getOrElse(FunctionThrowingCheckedExceptions<A> f, A defaultValue) {
        try {
            return f.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <A> Optional<A> maybeGet(FunctionThrowingCheckedExceptions<A> f) {
        try {
            return Optional.ofNullable(f.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}