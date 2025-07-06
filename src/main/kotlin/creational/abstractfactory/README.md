## Also known as

* Kit

![img.png](https://refactoring.guru/images/patterns/content/abstract-factory/abstract-factory-en-1.5x.png)

## Intent of Factory Method Design Pattern

Provides an `interface` for creating families of related or dependent objects without specifying their concrete classes, 
enhancing modularity and flexibility in software design.

## Problem

> App cửa hàng đồ nội thất.
> 1. A family of related products: `Chair` + `Sofa` + `CoffeeTable`.
> 2. Each family products have variants: `Modern`, `Victorian`, `ArtDeco`
> 
> Bạn cần một cách để tạo ra các đồ nội thất riêng lẻ sao cho chúng phù hợp với các đồ vật khác cùng loại:
> - Ví dụ: Huy mua Chair-Modern, và cần mua thêm Sofa-Modern. Nhưng shop lại ship Sofa-ArtDeco

![img.png](https://refactoring.guru/images/patterns/diagrams/abstract-factory/problem-en-1.5x.png)

## Solution

![img.png](https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure-1.5x.png)

## When to Use the Factory Method Pattern

* The system should be independent of how its products are created, composed, and represented.
* You need to configure the system with one of multiple families of products.
* A family of related product objects must be used together, enforcing consistency.
* Dependencies need to be constructed using runtime values or parameters.
* You need to choose which product to use from a family at runtime.
* Adding new products or families should not require changes to existing code.

## Benefits and Trade-offs of Factory Method Pattern

⚠️ tăng chi phí ban đầu + giảm tính minh bạch (do client dùng product thông qua factories)

## Real-World Applications of Factory Method Pattern in Java

* Java Swing's `LookAndFeel` classes for providing different look-and-feel options.
* Various implementations in the Java Abstract Window Toolkit (AWT) for creating different GUI components.
* [javax.xml.parsers.DocumentBuilderFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/parsers/DocumentBuilderFactory.html)
* [javax.xml.transform.TransformerFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/transform/TransformerFactory.html#newInstance--)
* [javax.xml.xpath.XPathFactory](http://docs.oracle.com/javase/8/docs/api/javax/xml/xpath/XPathFactory.html#newInstance--)

