Lazy Sequences Design Pattern
=============================
This design pattern is derived from Functional Programming language, and can be applied in java 8.

A _Lazy sequence_ is a data structure that is computed only when its elements are actually needed. All operations on lazy sequences, like _map()_ and _filter()_ are lazy are well, ppostponing invocation up to the moment when it is really necessary.
Lazy sequences are always traversed from the beginning using very cheap first / rest decomposition.

#### Class Diagram ####
![Alt text](lazy-sequence-class-diag.png?raw=true "Lazy Sequence Pattern")
