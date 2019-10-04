package torus;

@FunctionalInterface
interface FunctionThrowingCheckedExceptions<A> {
    A get() throws Exception;
}