## Intent

**Bridge** is a structural design pattern that lets you split a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently of each other.

![adapter.png](https://refactoring.guru/images/patterns/content/bridge/bridge-1.5x.png)

## Problem

> Say you have a geometric Shape class with a pair of subclasses: Circle and Square. You want to extend this class hierarchy to incorporate colors, so you plan to create Red and Blue shape subclasses.

![problem.png](https://refactoring.guru/images/patterns/diagrams/bridge/problem-en-1.5x.png)

## Solution
This problem occurs because we’re trying to extend the shape classes in **two independent dimensions: by form and by color**.

![solution.png](https://refactoring.guru/images/patterns/diagrams/bridge/solution-en-1.5x.png)

From now on, adding new colors won’t require changing the shape hierarchy, and vice versa.

### Abstraction and Implementation
_Abstraction_ (also called _interface_) is a high-level control layer for some entity. This layer isn’t supposed to do any real work on its own. 
It should delegate the work to the _implementation_ layer (also called _platform_).
- Note that we’re not talking about _interfaces_ or _abstract classes_ from your programming language. These aren’t the same things.

![Managing changes is much easier in modular code.png](https://refactoring.guru/images/patterns/content/bridge/bridge-3-en-1.5x.png)

> Making even a simple change to a monolithic codebase is pretty hard because you must understand the entire thing very well. Making changes to smaller, well-defined modules is much easier.

## Structure

![Structure.png](https://refactoring.guru/images/patterns/diagrams/bridge/structure-en-1.5x.png)


## When to Use

- **Use the Bridge pattern when you want to divide and organize a monolithic class that has several variants of some functionality (for example, if the class can work with various database servers).**
- **Use the pattern when you need to extend a class in several independent dimensions.**
- **Use the Bridge if you need to be able to switch implementations at runtime.**

> By the way, this last item is the main reason why so many people confuse the `Bridge` with the `Strategy` pattern. Remember that a pattern is more than just a certain way to structure your classes. 
> It may also communicate intent and a problem being addressed.

## Real-World Applications of Adapter Method Pattern in Java

* GUI Frameworks where the abstraction is the window, and the implementation could be the underlying OS windowing system.
* Database Drivers where the abstraction is a generic database interface, and the implementations are database-specific drivers.
* Device Drivers where the abstraction is the device-independent code, and the implementation is the device-dependent code.
