**🍩 Torus**

A simple additional functional programming support for Java 8+.

----

_1. Applicative Functor_
  - Apply(f(a -> b -> ...) <\*> a ...) and lift((a -> b -> ...) <$> a <\*> b ...) functions for List, Stream, Optional and CompletedFuture classes.
  - Functions to be applied must be in curried form.
  
_2. Curry_
  - Currying for uncurried functions.
  - Parameter order flipping for curried functions.
  - Currying supports functions with upto 4 parameters.
  
_3. List Split_
  - Splits a list to chunks of size N. Returns List<List<T\>>.
  
_4. Tuple_
  - Pair(2-Tuple) and Triplet(3-Tuple) classes with zip/unzip functions from/to lists or streams and.
  - Packs Nth elements from each lists or streams and converts them to stream of tuples.
  
_5. Collection Manipulation Wrappers_
  - Returns the collection itself after manipulations.
  - Separate classes for Collection, List, and Map.
  - e.g. ThisList.sort(l) returns the list itself after sorting, compared to void of java.util.Collections.sort(l).

<br>

Example usages included in test code.
