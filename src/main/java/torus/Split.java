package torus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Split {
    private static <A> List<List<A>> toChunksOf(List<List<A>> result, List<A> src, int size, int count, boolean copy) {
        if (count >= src.size()) {
            return result;
        }
        List<A> chunk = (count + size > src.size())
                ? src.subList(count, src.size())
                : src.subList(count, count + size);
        result.add(copy ? new ArrayList<>(chunk) : chunk);
        return toChunksOf(result, src, size, count + size, copy);
    }

    public static <A> List<List<A>> toChunksOf(List<A> src, int size) {
        return toChunksOf(new ArrayList<>(), src, size, 0, false);
    }

    public static <A> List<List<A>> toChunksOf(Stream<A> src, int size) {
        return toChunksOf(new ArrayList<>(), src.collect(Collectors.toList()), size, 0, false);
    }

    public static <A> List<List<A>> copiedToChunksOf(List<A> src, int size) {
        return toChunksOf(new ArrayList<>(), src, size, 0, true);
    }

    public static <A> List<List<A>> copiedToChunksOf(Stream<A> src, int size) {
        return toChunksOf(new ArrayList<>(), src.collect(Collectors.toList()), size, 0, true);
    }

    private Split() {}
}