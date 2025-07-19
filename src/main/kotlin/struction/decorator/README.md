## Intent

**Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

- Complexity: ⭐⭐
- Popularity: ⭐⭐

## Problem

Imagine that you’re working on a notification library which lets other programs notify their users about important events.

At some point, you realize that users of the library expect more than just email notifications.

![Structure of the library after creating class combinations](https://refactoring.guru/images/patterns/diagrams/decorator/problem3.png)

## Solution
In our notifications example, let’s leave the simple email notification behavior inside the base `Notifier` class, but turn all other notification methods into decorators.

![The solution with the Decorator pattern](https://refactoring.guru/images/patterns/diagrams/decorator/solution2.png)

Various notification methods become decorators.

The client code would need to wrap a basic notifier object into a set of decorators that match the client’s preferences. The resulting objects will be structured as a stack.

![Apps might configure complex stacks of notification decorators](https://refactoring.guru/images/patterns/diagrams/decorator/solution3-en.png)

Apps might configure complex stacks of notification decorators.

## Real-World Analogy

Wearing clothes is an example of using decorators. When you’re cold, you wrap yourself in a sweater. If you’re still cold with a sweater, you can wear a jacket on top

## Structure

![](https://refactoring.guru/images/patterns/diagrams/decorator/structure-indexed-1.5x.png)

## When to Use

- Sử dụng khi bạn cần có khả năng gán các hành vi bổ sung cho các đối tượng khi chạy mà không làm hỏng mã sử dụng các đối tượng này.
- Sử dụng mẫu khi việc mở rộng hành vi của đối tượng bằng cách kế thừa trở nên khó khăn hoặc không thể thực hiện được.

## Real-World Applications of Adapter Method Pattern in Java

-   All subclasses of [`java.io.InputStream`](http://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html), [`OutputStream`](http://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html), [`Reader`](http://docs.oracle.com/javase/8/docs/api/java/io/Reader.html) and [`Writer`](http://docs.oracle.com/javase/8/docs/api/java/io/Writer.html) have constructors that accept objects of their own type.
-   [`java.util.Collections`](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html), methods [`checkedXXX()`](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#checkedCollection-java.util.Collection-java.lang.Class-), [`synchronizedXXX()`](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedCollection-java.util.Collection-) and [`unmodifiableXXX()`](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableCollection-java.util.Collection-).
-   [`javax.servlet.http.HttpServletRequestWrapper`](http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequestWrapper.html) and [`HttpServletResponseWrapper`](http://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletResponseWrapper.html)

## Pseudocode

In this example, the **Decorator** pattern lets you compress and encrypt sensitive data independently from the code that actually uses this data.

![Structure of the Decorator pattern example](https://refactoring.guru/images/patterns/diagrams/decorator/example.png)

The encryption and compression decorators example.

The application wraps the data source object with a pair of decorators. Both wrappers change the way the data is written to and read from the disk:

-   Just before the data is **written to disk**, the decorators encrypt and compress it. The original class writes the encrypted and protected data to the file without knowing about the change.

-   Right after the data is **read from disk**, it goes through the same decorators, which decompress and decode it.
