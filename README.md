**Torus**

A simple functional programming support for Java language.

----

1. Applicative Functor
  - Apply(a <*> b <*> ...) and lift (a <$> b <*> ...) functions for List, Stream, Optional and CompletedFuture classes.
  
2. Curry
  - Currying and parameter order flip functions for uncurried functions.
  
3. List Split
  - Splits a list to chunks of size N. Returns List<List<T>>.
  
4. Tuple
  - Pair(2-Tuple) and Triplet(3-Tuple) classes with zip functions.
  
5. Collection Manipulation Wrappers
  - Returns the collection itself after manipulations.
  - e.g. ThisList.sort() returns the list itself after sorting, compared to void of Collections.sort().