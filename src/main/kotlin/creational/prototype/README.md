## Intent

The Prototype pattern is used to specify the kinds of objects to create using a prototypical instance, 
and create new instances through object cloning.

## Problem

> Hãy tưởng tượng một công ty sản xuất đồ nội thất theo thiết kế riêng. 
> Thay vì tạo ra từng sản phẩm từ đầu mỗi khi có đơn hàng, họ giữ lại các nguyên mẫu của những thiết kế phổ biến nhất của mình. 
> Khi khách hàng đặt hàng một thiết kế cụ thể, công ty chỉ cần sao chép nguyên mẫu của thiết kế đó và thực hiện các tùy chỉnh cần thiết.

## Solution
The Prototype pattern delegates the cloning process to the actual objects that are being cloned

## Structure

![Structure.png](https://refactoring.guru/images/patterns/diagrams/prototype/structure-1.5x.png)

## When to Use the Factory Method Pattern

* Khi các lớp cần khởi tạo được xác định tại run-time, ví dụ, bằng dynamic loading.
* Để tránh xây dựng một hệ phân cấp lớp của factory song song với hệ phân cấp lớp của product.
* Khi các instance của một lớp chỉ có một vài tổ hợp trạng thái khác nhau. Sẽ thuận tiện hơn khi cài đặt một số lượng prototype tương ứng và clone chúng thay vì khởi tạo thủ công mỗi lần với trạng thái phù hợp.
* Khi việc tạo object tốn kém hơn so với việc clone.
* Khi các concrete class cần khởi tạo chưa được biết cho đến khi runtime.

## Benefits and Trade-offs of Factory Method Pattern

⚠️ Requires implementing a cloning mechanism which might be complex.

## Real-World Applications of Factory Method Pattern in Java

* In Java, the `Object.clone()` method is a classic implementation of the Prototype pattern.
* GUI libraries often use prototypes for creating buttons, windows, and other widgets.
* In game development, creating multiple objects (like `enemy` characters) with similar attributes.
