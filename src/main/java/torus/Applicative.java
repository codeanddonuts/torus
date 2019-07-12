package torus;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Applicative {
    public static <A, B> Stream<B> apply(List<Function<A, B>> wrappedFunctions, Stream<A> context) {
        return context.flatMap(x -> wrappedFunctions.stream().map(f -> f.apply(x)));
    }

    public static <A, B> Stream<B> apply(Stream<Function<A, B>> wrappedFunctions, List<A> context) {
        return wrappedFunctions.flatMap(f -> context.stream().map(f));
    }

    public static <A, B> Stream<B> apply(List<Function<A, B>> wrappedFunctions, List<A> context) {
        return apply(wrappedFunctions, context.stream());
    }

    public static <A, B> Stream<B> apply(Stream<Function<A, B>> wrappedFunctions, Stream<A> context) {
        return apply(wrappedFunctions.collect(Collectors.toList()), context);
    }

    public static <A, B> Optional<B> apply(Optional<Function<A, B>> wrappedFunction, Optional<A> optional) {
        return wrappedFunction.flatMap(optional::map);
    }

    public static <A, B, C> Optional<C> apply(
            Optional<Function<A, Function<B, C>>> wrappedFunction,
            Optional<A> optionalA,
            Optional<B> optionalB
    ) {
        return wrappedFunction.flatMap(f ->
                optionalA.flatMap(a ->
                        optionalB.map(b -> f.apply(a).apply(b))
                )
        );
    }

    public static <A, B, C, D> Optional<D> apply(
            Optional<Function<A, Function<B, Function<C, D>>>> wrappedFunction,
            Optional<A> optionalA,
            Optional<B> optionalB,
            Optional<C> optionalC
    ) {
        return wrappedFunction.flatMap(f ->
                optionalA.flatMap(a ->
                        optionalB.flatMap(b ->
                                optionalC.map(c -> f.apply(a).apply(b).apply(c))
                        )
                )
        );
    }

    public static <A, B, C, D, E> Optional<E> apply(
            Optional<Function<A, Function<B, Function<C, Function<D, E>>>>> wrappedFunction,
            Optional<A> optionalA,
            Optional<B> optionalB,
            Optional<C> optionalC,
            Optional<D> optionalD
    ) {
        return wrappedFunction.flatMap(f ->
                optionalA.flatMap(a ->
                        optionalB.flatMap(b ->
                                optionalC.flatMap(c ->
                                        optionalD.map(d -> f.apply(a).apply(b).apply(c).apply(d))
                                )
                        )
                )
        );
    }

    public static <A, B, C> Optional<C> apply(
            Optional<Function<A, Function<B, C>>> wrappedFunction,
            Pair<Optional<A>, Optional<B>> pairOfOptionals
    ) {
        return apply(wrappedFunction, pairOfOptionals.fst(), pairOfOptionals.snd());
    }

    public static <A, B, C, D> Optional<D> apply(
            Optional<Function<A, Function<B, Function<C, D>>>> wrappedFunction,
            Triplet<Optional<A>, Optional<B>, Optional<C>> tripletOfOptionals
    ) {
        return apply(wrappedFunction, tripletOfOptionals.fst(), tripletOfOptionals.snd(), tripletOfOptionals.trd());
    }

    private static <A, B> Optional apply(Optional<Function<A, B>> acc, Optional[] optionals, int count) {
        return (count == optionals.length) ? acc : apply(apply(acc, optionals[count]), optionals, count + 1);
    }

    public static <A, B> Optional apply(Optional<Function<A, B>> wrappedFunction, Optional ... optionals) {
        return apply(wrappedFunction, optionals, 0);
    }

    public static <A, B> Optional<B> lift(Function<A, B> function, Optional<A> optional) {
        return apply(Optional.of(function), optional);
    }

    public static <A, B, C> Optional<C> lift(
            Function<A, Function<B, C>> function,
            Optional<A> optionalA,
            Optional<B> optionalB
    ) {
        return apply(Optional.of(function), optionalA, optionalB);
    }

    public static <A, B, C, D> Optional<D> lift(
            Function<A, Function<B, Function<C, D>>> function,
            Optional<A> optionalA,
            Optional<B> optionalB,
            Optional<C> optionalC
    ) {
        return apply(Optional.of(function), optionalA, optionalB, optionalC);
    }

    public static <A, B, C, D, E> Optional<E> lift(
            Function<A, Function<B, Function<C, Function<D, E>>>> function,
            Optional<A> optionalA,
            Optional<B> optionalB,
            Optional<C> optionalC,
            Optional<D> optionalD
    ) {
        return apply(Optional.of(function), optionalA, optionalB, optionalC, optionalD);
    }

    public static <A, B, C> Optional<C> lift(
            Function<A, Function<B, C>> function,
            Pair<Optional<A>, Optional<B>> pairedOptionals
    ) {
        return apply(Optional.of(function), pairedOptionals.fst(), pairedOptionals.snd());
    }

    public static <A, B, C, D> Optional<D> lift(
            Function<A, Function<B, Function<C, D>>> function,
            Triplet<Optional<A>, Optional<B>, Optional<C>> tripleOptionals
    ) {
        return apply(Optional.of(function), tripleOptionals.fst(), tripleOptionals.snd(), tripleOptionals.trd());
    }

    public static Optional lift(Function function, Optional ... optionals) {
        return apply(Optional.of(function), optionals, 0);
    }

    public static Optional lift(Function function, List<Optional> optionals) {
        return apply(Optional.of(function), optionals.stream().toArray(Optional[]::new), 0);
    }

    public static Optional lift(Function function, Stream<Optional> optionals) {
        return apply(Optional.of(function), optionals.toArray(Optional[]::new), 0);
    }

    public static <A, B> CompletableFuture<B> apply(
            CompletableFuture<Function<A, B>> wrappedFunction,
            CompletableFuture<A> future
    ) {
        return wrappedFunction.thenCompose(future::thenApply);
    }

    public static <A, B, C> CompletableFuture<C> apply(
            CompletableFuture<Function<A, Function<B, C>>> wrappedFunction,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB

    ) {
        return wrappedFunction.thenCompose(f ->
                futureA.thenCompose(a ->
                        futureB.thenApply(b -> f.apply(a).apply(b))
                )
        );
    }

    public static <A, B, C, D> CompletableFuture<D> apply(
            CompletableFuture<Function<A, Function<B, Function<C, D>>>> wrappedFunction,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB,
            CompletableFuture<C> futureC

    ) {
        return wrappedFunction.thenCompose(f ->
                futureA.thenCompose(a ->
                        futureB.thenCompose(b ->
                                futureC.thenApply(c -> f.apply(a).apply(b).apply(c))
                        )
                )
        );
    }

    public static <A, B, C, D, E> CompletableFuture<E> apply(
            CompletableFuture<Function<A, Function<B, Function<C, Function<D, E>>>>> wrappedFunction,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB,
            CompletableFuture<C> futureC,
            CompletableFuture<D> futureD

    ) {
        return wrappedFunction.thenCompose(f ->
                futureA.thenCompose(a ->
                        futureB.thenCompose(b ->
                                futureC.thenCompose(c ->
                                        futureD.thenApply(d -> f.apply(a).apply(b).apply(c).apply(d))
                                )
                        )
                )
        );
    }

    public static <A, B, C> CompletableFuture<C> apply(
            CompletableFuture<Function<A, Function<B, C>>> wrappedFunction,
            Pair<CompletableFuture<A>, CompletableFuture<B>> pairOfOptionals
    ) {
        return apply(wrappedFunction, pairOfOptionals.fst(), pairOfOptionals.snd());
    }

    public static <A, B, C, D> CompletableFuture<D> apply(
            CompletableFuture<Function<A, Function<B, Function<C, D>>>> wrappedFunction,
            Triplet<CompletableFuture<A>, CompletableFuture<B>, CompletableFuture<C>> tripletOfFutures
    ) {
        return apply(wrappedFunction, tripletOfFutures.fst(), tripletOfFutures.snd(), tripletOfFutures.trd());
    }

    private static <A, B> CompletableFuture apply(
            CompletableFuture<Function<A, B>> acc,
            CompletableFuture[] args,
            int count
    ) {
        return (count == args.length) ? acc : apply(apply(acc, args[count]), args, count + 1);
    }

    public static <A, B> CompletableFuture apply(
            CompletableFuture<Function<A, B>> wrappedFunction,
            CompletableFuture ... futures
    ) {
        return apply(wrappedFunction, futures, 0);
    }

    public static <A, B> CompletableFuture apply(
            CompletableFuture<Function<A, B>> wrappedFunction,
            Stream<CompletableFuture> futures
    ) {
        return apply(wrappedFunction, futures.toArray(CompletableFuture[]::new), 0);
    }

    public static <A, B> CompletableFuture<B> lift(Function<A, B> function, CompletableFuture<A> future) {
        return apply(CompletableFuture.completedFuture(function), future);
    }

    public static <A, B, C> CompletableFuture<C> lift(
            Function<A, Function<B, C>> function,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB
    ) {
        return apply(CompletableFuture.completedFuture(function), futureA, futureB);
    }

    public static <A, B, C, D> CompletableFuture<D> lift(
            Function<A, Function<B, Function<C, D>>> function,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB,
            CompletableFuture<C> futureC
    ) {
        return apply(CompletableFuture.completedFuture(function), futureA, futureB, futureC);
    }

    public static <A, B, C, D, E> CompletableFuture<E> lift(
            Function<A, Function<B, Function<C, Function<D, E>>>> function,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB,
            CompletableFuture<C> futureC,
            CompletableFuture<D> futureD
    ) {
        return apply(CompletableFuture.completedFuture(function), futureA, futureB, futureC, futureD);
    }

    public static <A, B, C, D> CompletableFuture<D> liftF(
            Function<A, Function<B, Function<C, D>>> function,
            CompletableFuture<A> futureA,
            CompletableFuture<B> futureB,
            CompletableFuture<C> futureC
    ) {
        return apply(CompletableFuture.completedFuture(function), futureA, futureB, futureC);
    }

    public static CompletableFuture lift(Function function, CompletableFuture ... futures) {
        return apply(CompletableFuture.completedFuture(function), futures, 0);
    }

    private Applicative() {}
}