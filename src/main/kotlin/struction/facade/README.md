# Facade

## **Intent**

**Facade** là một *structural design pattern* (mẫu thiết kế cấu trúc) cung cấp một **giao diện đơn giản** cho một **thư viện**, **framework**, hoặc bất kỳ **tập hợp phức tạp** nào của các *class*.

![Facade design pattern](https://refactoring.guru/images/patterns/content/facade/facade.png)

---
- Complexity: ⭐
- Popularity: ⭐⭐

## **Problem**

Khi cần làm việc với **một tập hợp lớn các object** từ một **thư viện hoặc framework phức tạp**, bạn sẽ phải:

* Khởi tạo nhiều object
* Quản lý các *dependency*
* Gọi đúng thứ tự các phương thức

Điều này khiến *business logic* trong *class* của bạn **phụ thuộc chặt chẽ** vào chi tiết triển khai của thư viện bên thứ 3, gây **khó hiểu và khó bảo trì**.

---

## **Solution**

**Facade** là một *class* cung cấp **giao diện đơn giản** để làm việc với **subsystem phức tạp** có nhiều thành phần. Nó chỉ bao gồm các tính năng mà **client thực sự cần dùng**.

Ví dụ: một ứng dụng chỉ cần chức năng `encode(filename, format)` từ một thư viện xử lý video chuyên nghiệp. Việc tạo một *class* đơn giản cung cấp phương thức này và kết nối nó với thư viện → chính là một **facade**.

---

## **Real-World Analogy**

![An example of taking a phone order](https://refactoring.guru/images/patterns/diagrams/facade/live-example-en.png)

**Đặt hàng qua điện thoại**: Người nhận cuộc gọi chính là **facade** — cung cấp một giao diện thoại đơn giản để bạn truy cập hệ thống đặt hàng, thanh toán và giao hàng phức tạp phía sau.

## Structure

![](https://refactoring.guru/images/patterns/diagrams/facade/structure-1.5x.png)

Dưới đây là bản tóm tắt phần **Applicability** của mẫu thiết kế **Facade** bằng tiếng Việt, giữ nguyên các thuật ngữ kỹ thuật và liên kết hình ảnh/giao diện:

---

## **Applicability**

Sử dụng **Facade pattern** khi bạn cần cung cấp một **giao diện đơn giản và giới hạn** cho một **subsystem phức tạp**.

* Theo thời gian, **subsystem** thường trở nên **phức tạp hơn** do áp dụng thêm các *design pattern* và tạo thêm nhiều *class*.
* Mặc dù subsystem có thể trở nên linh hoạt và dễ tái sử dụng, nhưng lại yêu cầu nhiều **cấu hình** và **boilerplate code** hơn từ phía *client*.
* **Facade** giúp đơn giản hóa điều này bằng cách cung cấp **đường tắt** (shortcut) đến những tính năng thường dùng nhất, phù hợp với hầu hết nhu cầu của *client*.

Sử dụng **Facade** khi muốn **phân lớp subsystem**:

* Tạo *facade* cho từng lớp/layer để **xác định các điểm truy cập**.
* Việc này giúp **giảm sự phụ thuộc (coupling)** giữa các *subsystem*, vì chúng chỉ tương tác thông qua các *facade*.

**Ví dụ**: với một *video conversion framework*, bạn có thể chia thành 2 lớp: *video* và *audio*. Tạo một *facade* cho mỗi lớp và để các lớp giao tiếp thông qua các *facade* đó.
Cách tiếp cận này **rất giống với [Mediator pattern](https://refactoring.guru/design-patterns/mediator)**.


## Pros and Cons

- You can isolate your code from the complexity of a subsystem.

- 😭 A facade can become [a god object](https://refactoring.guru/antipatterns/god-object) coupled to all classes of an app

## Pseudocode

In this example, the **Facade** pattern simplifies interaction with a complex video conversion framework.

![The structure of the Facade pattern example](https://refactoring.guru/images/patterns/diagrams/facade/example.png)
