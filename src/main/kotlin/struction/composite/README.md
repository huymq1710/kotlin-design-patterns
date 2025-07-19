## Intent

**Composite** is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

![Composite design pattern](https://refactoring.guru/images/patterns/content/composite/composite.png)

- Complexity: ⭐⭐
- Popularity: ⭐⭐

## Problem

Using the Composite pattern makes sense only when the core model of your app can be represented as a tree.

> For example, imagine that you have two types of objects: `Products` and `Boxes`. A `Box` can contain several `Products` as well as a number of smaller `Boxes`. These little `Boxes` can also hold some `Products` or even smaller `Boxes`, and so on.

![Structure of a complex order](https://refactoring.guru/images/patterns/diagrams/composite/problem-en.png)

An order might comprise various products, packaged in boxes, which are packaged in bigger boxes and so on. The whole structure looks like an upside down tree.

## Solution
The Composite pattern suggests that you work with `Products` and `Boxes` through a common interface which declares a method for calculating the total price.

![Solution suggested by the Composite pattern](https://refactoring.guru/images/patterns/content/composite/composite-comic-1-en.png)

The Composite pattern lets you run a behavior recursively over all components of an object tree.

## Real-World Analogy

![An example of a military structure](https://refactoring.guru/images/patterns/diagrams/composite/live-example.png)

An example of a military structure.

- Armies of most countries are structured as hierarchies. An army consists of several divisions; a division is a set of brigades, and a brigade consists of platoons, which can be broken down into squads

## Structure

![Structure of the Composite design pattern](https://refactoring.guru/images/patterns/diagrams/composite/structure-en.png)

## When to Use

- **Use the Composite pattern when you have to implement a tree-like object structure.**
- **Use the pattern when you want the client code to treat both simple and complex elements uniformly.**

## Real-World Applications of Adapter Method Pattern in Java

* [Flyweight](https://java-design-patterns.com/patterns/flyweight/): Composite can use Flyweight to share component instances among several composites.
* [Iterator](https://java-design-patterns.com/patterns/iterator/): Can be used to traverse Composite structures.
* [Visitor](https://java-design-patterns.com/patterns/visitor/): Can apply an operation over a Composite structure.
