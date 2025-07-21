# Observer

Còn được gọi là: Event-Subscriber, Listener  
🔁 **Observer** là một *behavioral design pattern* cho phép bạn định nghĩa cơ chế đăng ký để thông báo nhiều đối tượng khác nhau về các sự kiện xảy ra trong đối tượng mà họ đang quan sát.

- Complexity: ⭐⭐
- Popularity: ⭐⭐⭐

![Observer Design Pattern](https://refactoring.guru/images/patterns/content/observer/observer.png)

## Intent

🎯 Mục đích: Định nghĩa cơ chế cho phép nhiều đối tượng (subscribers) đăng ký để nhận thông báo khi đối tượng (publisher) thay đổi trạng thái.

## Problem

Giả sử bạn có hai đối tượng: `Customer` và `Store`.  
Khách hàng quan tâm đến một sản phẩm mới (ví dụ: iPhone mới), và có thể phải đến cửa hàng mỗi ngày để kiểm tra.

![Visiting store vs. sending spam](https://refactoring.guru/images/patterns/content/observer/observer-comic-1-en.png)

Hoặc cửa hàng có thể gửi email hàng loạt đến mọi khách hàng – nhưng điều đó dẫn đến spam.

🎭 Vấn đề: Lãng phí thời gian của khách hoặc tài nguyên của cửa hàng nếu không có cách nào thông minh hơn để thông báo khi sản phẩm mới có sẵn.

## Solution

📢 Giải pháp là tách đối tượng có thông tin (publisher) và những đối tượng muốn nhận thông báo (subscribers).

➡️ Publisher chứa danh sách subscriber và cung cấp các phương thức để đăng ký/hủy đăng ký.

![Subscription mechanism](https://refactoring.guru/images/patterns/diagrams/observer/solution1-en.png)

Khi có sự kiện xảy ra, publisher sẽ gọi hàm thông báo trong mỗi subscriber.

🧩 Để tránh phụ thuộc chặt, tất cả subscriber cần triển khai cùng một interface – chứa phương thức thông báo (`update`, v.v.).

![Notification methods](https://refactoring.guru/images/patterns/diagrams/observer/solution2-en.png)

Bạn cũng có thể chuẩn hóa tất cả publisher theo một interface để subscriber dùng chung.

## Real-World Analogy

📬 Tương tự như đăng ký báo/tạp chí: bạn không cần ra cửa hàng kiểm tra mỗi ngày, mà sẽ nhận được báo tại nhà.

![Magazine and newspaper subscriptions](https://refactoring.guru/images/patterns/content/observer/observer-comic-2-en.png)

🗂️ Publisher giữ danh sách người đăng ký và gửi bản mới đến họ. Người dùng có thể hủy đăng ký bất cứ lúc nào.

## Structure

![Structure of the Observer design pattern](https://refactoring.guru/images/patterns/diagrams/observer/structure-indexed.png)

1. **Publisher** phát ra sự kiện khi trạng thái thay đổi, và chứa logic đăng ký/hủy đăng ký.
2. Khi có sự kiện, gọi phương thức thông báo trong subscriber.
3. **Subscriber interface** khai báo phương thức `update` (hoặc tương tự).
4. **Concrete Subscribers** xử lý sự kiện khi được thông báo. Tất cả triển khai cùng interface.
5. Publisher có thể truyền dữ liệu ngữ cảnh khi thông báo, ví dụ: chính nó (`this`).
6. **Client** tạo publisher và subscriber rồi đăng ký các subscriber.

## Pseudocode

Ví dụ về ứng dụng trong Text Editor – cho phép đối tượng editor thông báo cho các service khác khi thay đổi trạng thái.

![Structure of the Observer pattern example](https://refactoring.guru/images/patterns/diagrams/observer/example.png)

📌 Danh sách subscriber có thể thay đổi tại runtime. Editor không tự quản lý mà sử dụng một helper object chuyên phụ trách việc đó.

➡️ Giúp dễ dàng mở rộng hệ thống, thêm subscriber mà không cần sửa publisher.

## Applicability

📌 Sử dụng khi:

- Một đối tượng thay đổi có thể ảnh hưởng đến đối tượng khác.
- Không biết trước đối tượng nào cần được thông báo.
- Danh sách subscriber thay đổi linh hoạt theo thời gian.

Ví dụ: Tạo custom button và cho phép người dùng gắn code chạy khi button được nhấn.

## Pros and Cons

✅ Ưu điểm:

- Tuân thủ nguyên lý **Open/Closed**: Thêm subscriber mới mà không sửa publisher.
- Kết nối linh hoạt giữa các đối tượng tại runtime.

⚠️ Nhược điểm:

- Thứ tự thông báo đến subscriber là ngẫu nhiên.

## Relations with Other Patterns

💡 Liên hệ với các pattern khác:

- [Chain of Responsibility](https://refactoring.guru/design-patterns/chain-of-responsibility): Truyền request dọc chuỗi cho đến khi có đối tượng xử lý.
- [Command](https://refactoring.guru/design-patterns/command): Kết nối một chiều giữa sender và receiver.
- [Mediator](https://refactoring.guru/design-patterns/mediator): Giao tiếp gián tiếp qua một đối tượng trung gian.
- **Observer**: Cho phép receiver đăng ký/hủy đăng ký nhận sự kiện.

👀 So sánh **Mediator** và **Observer**:

- Mediator loại bỏ sự phụ thuộc giữa các thành phần bằng cách chuyển mọi giao tiếp qua một đối tượng trung gian.
- Observer tạo các kết nối một chiều linh hoạt – không tập trung vào một điểm chung.

Trong một số trường hợp, Mediator có thể được cài đặt bằng Observer nếu mediator là publisher và các component là subscriber.

## Summary

👂 **Observer** giúp bạn thiết lập cơ chế đăng ký — nơi nhiều đối tượng có thể "lắng nghe" và phản hồi khi một đối tượng phát sinh sự kiện.  
📬 Phù hợp cho các hệ thống có nhiều thành phần cần cập nhật theo thời gian thực như GUI, hệ thống thông báo, hệ thống plugin...  
💡 Cho phép thêm hoặc gỡ bỏ subscribers một cách linh hoạt trong runtime, đồng thời tuân theo nguyên tắc **Open/Closed** (mở để mở rộng, đóng để chỉnh sửa).
