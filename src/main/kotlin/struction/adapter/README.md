## Intent

Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.

![adapter.png](https://refactoring.guru/images/patterns/content/adapter/adapter-en-2x.png)

## Problem

> Imagine that you’re creating a stock market monitoring app. The app downloads the stock data from multiple sources in XML format.
>
> You decide to improve the app by integrating a smart 3rd-party analytics library. But there’s a catch: the analytics library only works with data in JSON format.

![problem.png](https://refactoring.guru/images/patterns/diagrams/adapter/problem-en-2x.png)

In plain words

> Adapter pattern lets you wrap an otherwise incompatible object in an adapter to make it compatible with another class.

## Solution
You can create an adapter. This is a special object that converts the interface of one object so that another object can understand it.

![solution.png](https://refactoring.guru/images/patterns/diagrams/adapter/solution-en-2x.png)

Example code:

![Example.png](https://www.grubbadvertising.com/uploads/1/2/3/9/123923775/shutterstock-39299461_1_orig.jpg)

## Structure
### Object adapter

This implementation uses the object composition principle: the adapter implements the interface of one object and wraps the other one. It can be implemented in all popular programming languages.

![Structure.png](https://refactoring.guru/images/patterns/diagrams/adapter/structure-object-adapter-2x.png)

### Class adapter
This implementation uses inheritance: the adapter inherits interfaces from both objects at the same time. Note that this approach can only be implemented in programming languages that support multiple inheritance, such as C++.

![Structure.png](https://refactoring.guru/images/patterns/diagrams/adapter/structure-class-adapter-2x.png)

## When to Use the Adapter Method Pattern

> Use the Adapter class when you want to use some existing class, but its interface isn’t compatible with the rest of your code.

> Use the pattern when you want to reuse several existing subclasses that lack some common functionality that can’t be added to the superclass.


## Benefits and Trade-offs of Adapter Method Pattern

Class adapter và object adapter có những lợi ích và hạn chế khác nhau.

- Class adapter điều chỉnh Adaptee thành Target bằng cách liên kết với một Adaptee cụ thể, nghĩa là nó không thể thích ứng cho class và tất cả các subclass của nó. Loại adapter này cho phép Adapter override một số hành vi của Adaptee vì Adapter là subclass của Adaptee. Ngoài ra, nó chỉ tạo ra một object mà không cần thêm con trỏ trung gian để truy cập Adaptee.

- Object adapter cho phép một Adapter làm việc với nhiều Adaptee, bao gồm cả Adaptee và tất cả các subclass của nó. Loại adapter này có thể bổ sung chức năng cho tất cả Adaptee cùng lúc. Tuy nhiên, việc override hành vi của Adaptee sẽ khó hơn, vì cần phải tạo subclass của Adaptee và để Adapter tham chiếu đến subclass đó thay vì chính Adaptee.

## Real-World Applications of Adapter Method Pattern in Java

* `java.io.InputStreamReader` and `java.io.OutputStreamWriter` in the Java IO library.
* GUI component libraries that allow for plug-ins or adapters to convert between different GUI component interfaces.
* [java.util.Arrays#asList()](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList%28T...%29)
* [java.util.Collections#list()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#list-java.util.Enumeration-)
* [java.util.Collections#enumeration()](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#enumeration-java.util.Collection-)
* [javax.xml.bind.annotation.adapters.XMLAdapter](http://docs.oracle.com/javase/8/docs/api/javax/xml/bind/annotation/adapters/XmlAdapter.html#marshal-BoundType-)
