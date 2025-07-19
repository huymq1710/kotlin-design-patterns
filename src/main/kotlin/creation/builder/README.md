## Intent
- Complexity: ⭐⭐
- Popularity: ⭐⭐⭐

Lets you construct complex objects step by step. 

The pattern allows you to produce different types and representations of an object using the same construction code.

![img.png](https://refactoring.guru/images/patterns/content/builder/builder-en-1.5x.png)

## Problem

> Khi cần khởi tạo một object phức tạp với nhiều field và object lồng nhau, ta thường phải viết một constructor rất dài với nhiều tham số, hoặc tệ hơn là để logic khởi tạo rải rác trong client code.
>
> Ví dụ, để xây một `House`, bạn cần tường, sàn, cửa, cửa sổ, mái... Nếu muốn thêm các tính năng như sân sau, hệ thống sưởi, điện nước,... bạn sẽ cần thêm nhiều subclass để bao quát tất cả tổ hợp cấu hình. Việc thêm một tùy chọn mới (ví dụ kiểu hiên nhà) sẽ lại làm tăng số lượng subclass.

![img.png](https://refactoring.guru/images/patterns/diagrams/builder/problem1-1.5x.png)

> Nếu gom tất cả tham số vào một constructor lớn trong class House, ta sẽ không cần nhiều subclass nữa. Tuy nhiên, cách này tạo ra constructor có quá nhiều tham số, phần lớn trong số đó không được dùng trong đa số trường hợp.

![img.png](https://refactoring.guru/images/patterns/diagrams/builder/problem2-1.5x.png)



## Solution
The Builder pattern suggests that you extract the object construction code out of its own class and move it to separate objects called builders.

![img.png](https://refactoring.guru/images/patterns/diagrams/builder/solution1-1.5x.png)

You can go further and extract a series of calls to the builder steps you use to construct a product into a separate class called `director`.
The `director` class defines the order in which to execute the building steps, while the builder provides the implementation for those steps.

## Structure

![Structure.png](https://refactoring.guru/images/patterns/diagrams/builder/structure-1.5x.png)

## When to Use the Factory Method Pattern

* Requiring complex object creation (Tạo nhà, tạo xe,...).
* It's particularly useful when a product requires a lot of steps to be created and when these steps need to be executed in a specific sequence

## Benefits and Trade-offs of Factory Method Pattern

⚠️ tăng code complexity  + tăng memory (do tạo multiple builder objects)

## Real-World Applications of Factory Method Pattern in Java

* StringBuilder in Java for constructing strings.
* java.lang.StringBuffer used to create mutable string objects.
* Java.nio.ByteBuffer as well as similar buffers such as FloatBuffer, IntBuffer, and others
* javax.swing.GroupLayout.Group#addComponent()
* Various GUI builders in IDEs that construct UI components.
* All implementations of [java.lang.Appendable](http://docs.oracle.com/javase/8/docs/api/java/lang/Appendable.html)
* [Apache Camel builders](https://github.com/apache/camel/tree/0e195428ee04531be27a0b659005e3aa8d159d23/camel-core/src/main/java/org/apache/camel/builder)
* [Apache Commons Option.Builder](https://commons.apache.org/proper/commons-cli/apidocs/org/apache/commons/cli/Option.Builder.html)
