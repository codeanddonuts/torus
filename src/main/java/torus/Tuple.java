package torus;

public interface Tuple<A, B> {
    int size();
    A fst();
    B snd();
}