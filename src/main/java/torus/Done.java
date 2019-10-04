package torus;

@FunctionalInterface
public interface Done<T> extends TailRecursion<T> {
    @Override
    default TailRecursion<T> call() {
        return null;
    }
}