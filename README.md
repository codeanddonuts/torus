**🍩 Torus**

A simple additional functional programming support for Java 8+.

----

_1. Applicative Functor_
  - Apply(a <\*> b <\*> ...) and lift(a <$> b <\*> ...) functions for List, Stream, Optional and CompletedFuture classes.
  
_2. Curry_
  - Currying and parameter order flip functions for uncurried functions.
  - Currying supports upto functions with 4 parameters.
  - Supports curry and flip for BiFunctions.
  
_3. List Split_
  - Splits a list to chunks of size N. Returns List<List<T\>>.
  
_4. Tuple_
  - Pair(2-Tuple) and Triplet(3-Tuple) classes with zip functions from lists or streams.
  - Packs Nth element from each lists or streams and converts them to stream of tuples.
  
_5. Collection Manipulation Wrappers_
  - Returns the collection itself after manipulations.
  - e.g. ThisList.sort(l) returns the list itself after sorting, compared to void of Collections.sort(l).

Example usages included in test codes.