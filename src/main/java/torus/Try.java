package torus;

import java.util.Optional;
import java.util.function.Supplier;

public class Try {
    public static void execute(Subroutine subroutine) {
        try {
            subroutine.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A get(Supplier<A> function) {
        try {
            return function.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A getOrElse(Supplier<A> function, A defaultValue) {
        try {
            return function.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static <A> Optional<A> maybeGet(Supplier<A> function) {
        try {
            return Optional.ofNullable(function.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

@FunctionalInterface
interface Subroutine {
    void execute();
}