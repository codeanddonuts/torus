package torus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Split {
    private static <T> List<List<T>> toChunksOf(List<List<T>> result, List<T> src, int size, int count, boolean copy) {
        if (count >= src.size()) {
            return result;
        }
        List<T> chunk = (count + size > src.size())
                ? src.subList(count, src.size())
                : src.subList(count, count + size);
        result.add(copy ? new ArrayList<>(chunk) : chunk);
        return toChunksOf(result, src, size, count + size, copy);
    }

    public static <T> List<List<T>> toChunksOf(List<T> src, int size) {
        return toChunksOf(new ArrayList<>(), src, size, 0, false);
    }

    public static <T> List<List<T>> toChunksOf(Stream<T> src, int size) {
        return toChunksOf(new ArrayList<>(), src.collect(Collectors.toList()), size, 0, false);
    }

    public static <T> List<List<T>> copiedToChunksOf(List<T> src, int size) {
        return toChunksOf(new ArrayList<>(), src, size, 0, true);
    }

    public static <T> List<List<T>> copiedToChunksOf(Stream<T> src, int size) {
        return toChunksOf(new ArrayList<>(), src.collect(Collectors.toList()), size, 0, true);
    }

    private Split() {}
}