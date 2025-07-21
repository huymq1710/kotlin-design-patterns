# Visitor
- Complexity: ⭐⭐⭐
- Popularity: ⭐
## Intent

**Visitor** là một pattern hành vi (behavioral design pattern) cho phép bạn tách riêng thuật toán khỏi các object mà nó vận hành trên đó.  
📦 Giúp bạn dễ dàng thêm hành vi mới mà không sửa đổi class hiện tại.

![Visitor Design Pattern](https://refactoring.guru/images/patterns/content/visitor/visitor.png)

## Problem

🚧 Khi ứng dụng xử lý dữ liệu địa lý dưới dạng đồ thị phức tạp, bạn cần export nó sang XML.  
👉 Nhưng lại không được sửa các class node vì đã chạy production.  
⚠️ Việc nhồi nhét thêm logic export XML vào các class như City, Industry, SightSeeing vừa dễ gây bug, vừa sai trách nhiệm.

![Exporting the graph into XML](https://refactoring.guru/images/patterns/diagrams/visitor/problem1.png)

🧩 Dù ban đầu bạn định dùng polymorphism, nhưng vì không thể thay đổi class, giải pháp đó không khả thi.

![The XML export method had to be added into all node classes](https://refactoring.guru/images/patterns/diagrams/visitor/problem2-en.png)

## Solution

✅ Visitor đề xuất bạn đưa hành vi mới (ví dụ: export XML) vào một class riêng – gọi là *visitor*.  
📥 Thay vì mỗi object tự xử lý export, nó chỉ cần *chấp nhận visitor* (accept) và giao việc lại cho visitor.

📌 Để tránh việc dùng nhiều `instanceof`, pattern sử dụng kỹ thuật [Double Dispatch](https://refactoring.guru/design-patterns/visitor-double-dispatch) – cho phép object biết cách gọi đúng method phù hợp từ visitor.

Ví dụ:
```kotlin
// Client code
foreach (Node node in graph)
    node.accept(exportVisitor)

// City
class City is
    method accept(Visitor v) is
        v.doForCity(this)
````

🛠 Mặc dù phải chỉnh lại các node một chút để thêm `accept()`, nhưng thay đổi này nhỏ và có thể tái sử dụng nhiều hành vi khác nhau thông qua các visitor mới.

## Real-World Analogy

![Insurance agent](https://refactoring.guru/images/patterns/content/visitor/visitor-comic-1.png)

🏢 Một nhân viên bảo hiểm đến từng tòa nhà để bán bảo hiểm phù hợp:

* 🏠 Nhà dân → bảo hiểm y tế
* 🏦 Ngân hàng → bảo hiểm trộm cắp
* ☕ Quán cafe → bảo hiểm cháy/nước

🎯 Mỗi loại building sẽ “accept” agent, và chính nó quyết định agent nên làm gì.

## Structure

![Structure of the Visitor design pattern](https://refactoring.guru/images/patterns/diagrams/visitor/structure-en-indexed.png)

1. 🧭 **Visitor interface** định nghĩa nhiều phương thức tương ứng với từng class element cụ thể.
2. 🔁 **Concrete Visitor** cài đặt các hành vi cụ thể cho từng loại element.
3. 🔗 **Element interface** cung cấp phương thức `accept()` để nhận visitor.
4. 🧱 **Concrete Element** triển khai `accept()`, và gọi đúng hàm visitor tương ứng.
5. 👤 **Client** là nơi chứa tập hợp các element và truyền visitor cho từng phần tử.

## Pseudocode

🏗 Ví dụ, Visitor được dùng để export hình học (shapes) sang XML.

![Structure of the Visitor pattern example](https://refactoring.guru/images/patterns/diagrams/visitor/example.png)

📎 `accept()` cho phép visitor thực hiện hành vi phù hợp mà không cần dùng `instanceof`.

## Applicability

✅ Dùng Visitor khi:

* Cần thực hiện hành vi trên tập hợp object có nhiều class khác nhau.
* Muốn giữ logic chính trong class và đẩy phụ trách vụ khác sang nơi khác.
* Một hành vi chỉ áp dụng cho một số class nhất định trong hệ thống.

## Pros and Cons

✅ Ưu điểm:

* Tuân theo *Open/Closed Principle*: thêm hành vi mới mà không sửa code cũ.
* Tuân theo *Single Responsibility Principle*: phân tách hành vi phụ ra khỏi logic chính.
* Có thể gom dữ liệu xuyên suốt khi đi qua các object.

⚠️ Nhược điểm:

* Mỗi khi thêm một class element mới, phải cập nhật tất cả visitor liên quan.
* Visitor có thể bị giới hạn nếu không truy cập được field/method private của element.

## Relations with Other Patterns

🔗 Quan hệ với các pattern khác:

* Visitor giống như bản nâng cấp của [Command](https://refactoring.guru/design-patterns/command) – thao tác lên nhiều loại object khác nhau.
* Có thể kết hợp với [Composite](https://refactoring.guru/design-patterns/composite) để áp dụng hành vi xuyên suốt cây object.
* Kết hợp với [Iterator](https://refactoring.guru/design-patterns/iterator) để lặp qua data structure phức tạp và thao tác lên các phần tử.

## Summary

🧳 **Visitor** cho phép thêm hành vi mới mà không thay đổi class gốc.
🔁 Sử dụng kỹ thuật Double Dispatch để tránh `instanceof`.
🏗 Phù hợp với hệ thống phức tạp cần thêm hành vi mà không đụng đến class cũ.
🧠 Giúp tách biệt logic phụ, như export hay tính toán, ra khỏi core logic của object.

## Kotlin Implementation

This implementation demonstrates the Visitor pattern using Kotlin best practices:

### Key Kotlin Features Used:
- **Data Classes & Properties**: Clean, concise property definitions with `val`/`var`
- **Interface Segregation**: Clear separation of concerns with `Shape` and `Visitor` interfaces  
- **Extension Functions**: Could be used for additional operations (not shown but possible)
- **String Templates**: Clean string interpolation in XML generation
- **Builder Pattern**: Using `buildString {}` for efficient string construction
- **Collections**: Using `List<Shape>` with proper encapsulation via backing properties
- **Sealed Classes**: Could be used for a more functional approach (alternative implementation)

### Files Structure:
```
visitor/
├── Demo.kt                           # Main demonstration
├── shapes/
│   ├── Shape.kt                      # Common interface
│   ├── Dot.kt                        # Simple shape
│   ├── Circle.kt                     # Extends Dot
│   ├── Rectangle.kt                  # Standalone shape
│   └── CompoundShape.kt              # Composite pattern integration
└── visitor/
    ├── Visitor.kt                    # Visitor interface
    ├── XMLExportVisitor.kt           # XML export implementation
    └── AreaCalculatorVisitor.kt      # Area calculation implementation
```

### Running the Demo:
```bash
# Compile and run the demo
./gradlew run -Pmain=behavior.visitor.DemoKt
```

The example shows how to:
1. Export geometric shapes to XML format
2. Calculate areas of different shapes  
3. Handle compound shapes (Composite pattern integration)
4. Add new operations without modifying existing shape classes
