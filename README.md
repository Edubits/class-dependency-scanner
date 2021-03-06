Class Dependency Scanner [![Build Status](https://travis-ci.org/janbeernink/class-dependency-scanner.svg?branch=develop)](https://travis-ci.org/janbeernink/class-dependency-scanner)
========================

This is a small experimental Java library to analyze the dependencies between classes and generate a dependency graph.

Usage
========================

The following example shows how to generate a dependency graph for class String:

    new ClassDependencyScanner().buildDependencyGraph(String.class);

This will return a DependencyGraphNode that represents the String class. This node can be used as a starting point to iterate over the entire graph:

    for (DependencyGraphNode dependencyGraphNode: startingNode) {
        // Do something with each node
    }

The iterator returned by the iterator() method on DependencyGraphNode is guaranteed to visit each node exactly once, even when there are cycles in the graph.

Known issues
========================

* Annotations are not yet fully processed.

License
========================

This project is licensed under the Apache Software License, Version 2.0, please see the LICENSE file for more details.
