# Command

Also known as: Action, Transaction
- Complexity: ⭐
- Popularity: ⭐⭐⭐

## Intent

**Command** là một mẫu thiết kế hành vi (behavioral pattern) giúp biến một yêu cầu thành một đối tượng độc lập chứa toàn bộ thông tin về yêu cầu đó. Nhờ vậy, có thể:
- Truyền yêu cầu như tham số hàm
- Trì hoãn hoặc xếp hàng thực thi yêu cầu
- Hỗ trợ thao tác *undo*

![Command design pattern](https://refactoring.guru/images/patterns/content/command/command-en.png)

## Problem

🧠 Khi phát triển một ứng dụng soạn thảo văn bản:
- Có nhiều nút bấm giống nhau nhưng thực hiện các hành động khác nhau (Cut, Copy, Paste…)
- Nếu mỗi nút là một subclass chứa logic riêng ➡️ mã bị trùng lặp và phụ thuộc chặt chẽ
- Một hành động (như *Copy*) có thể được gọi từ toolbar, menu chuột phải hoặc phím tắt (`Ctrl+C`) — khó tái sử dụng

![Problem solved by the Command pattern](https://refactoring.guru/images/patterns/diagrams/command/problem1.png)
![Lots of button subclasses](https://refactoring.guru/images/patterns/diagrams/command/problem2.png)
![Several classes implement the same functionality](https://refactoring.guru/images/patterns/diagrams/command/problem3-en.png)

## Solution

💡 Nguyên lý thiết kế tốt là **phân tách mối quan tâm** (*separation of concerns*): chia ứng dụng thành các lớp riêng biệt như:
- Giao diện người dùng (GUI)
- Logic nghiệp vụ (Business Logic)

Command pattern đề xuất:
- Trích xuất yêu cầu thành một lớp riêng gọi là *command*
- GUI chỉ cần gọi command mà không biết chi tiết xử lý
- Mỗi command thực thi thông qua một phương thức duy nhất (`execute()`)

🎯 Lợi ích:
- Thay đổi hành vi của nút tại runtime
- Giảm phụ thuộc giữa GUI và logic
- Tránh trùng lặp mã

![The GUI layer may access the business logic layer directly](https://refactoring.guru/images/patterns/diagrams/command/solution1-en.png)

![Accessing the business logic layer via a command.](https://refactoring.guru/images/patterns/diagrams/command/solution2-en.png)

![The GUI objects delegate the work to commands](https://refactoring.guru/images/patterns/diagrams/command/solution3-en.png)

Ví dụ: thay vì tạo `CopyButton`, `PasteButton`, bạn chỉ cần:
- Tạo các command `CopyCommand`, `PasteCommand`
- Nút nào cần thao tác gì thì gán command tương ứng

👀 Kết quả: logic nghiệp vụ và giao diện tách biệt, dễ mở rộng, dễ bảo trì.

## Real-World Analogy

🧾 **Gọi món trong nhà hàng**:
- Bạn gọi món và nhân viên ghi đơn (Command)
- Đơn được đưa vào bếp
- Đầu bếp đọc đơn và thực hiện
- Khi hoàn tất, phục vụ đem món đến bàn

📌 Tờ giấy gọi món chính là *Command*:
- Có thể xếp hàng chờ
- Chứa đủ thông tin để xử lý
- Người phục vụ và đầu bếp không cần hỏi bạn lại

![Making an order in a restaurant](https://refactoring.guru/images/patterns/content/command/command-comic-1.png)

## Structure

![Structure of the Command design pattern](https://refactoring.guru/images/patterns/diagrams/command/structure.png)  
![Structure of the Command design pattern](https://refactoring.guru/images/patterns/diagrams/command/structure-indexed.png)

1. **Sender** (Invoker): nơi khởi động yêu cầu, chỉ lưu tham chiếu đến command, không xử lý trực tiếp
2. **Command Interface**: định nghĩa phương thức `execute()`
3. **Concrete Command**: triển khai các hành động cụ thể, chứa tham chiếu đến receiver
4. **Receiver**: thực hiện công việc thực tế (logic nghiệp vụ)
5. **Client**: tạo command, gán receiver và gửi cho sender

## Pseudocode

📝 Ví dụ ứng dụng trong trình soạn thảo văn bản:
- Mỗi thao tác như `Cut`, `Paste` tạo backup trước khi thực thi
- Command lưu vào *stack lịch sử*
- Khi *Undo*, chỉ cần gọi lại trạng thái từ lịch sử

![Structure of the Command pattern example](https://refactoring.guru/images/patterns/diagrams/command/example.png)

🎁 Lợi ích:
- Command không phụ thuộc vào GUI cụ thể
- Dễ mở rộng chức năng mới mà không phá vỡ code cũ

## Applicability

📌 Áp dụng Command Pattern khi:
- Muốn tham số hóa đối tượng bằng thao tác
- Cần xếp hàng, lên lịch hoặc thực thi yêu cầu từ xa
- Cần hỗ trợ thao tác *undo/redo*

🎯 Ví dụ:
- Gán thao tác cho context menu
- Gửi lệnh qua mạng hoặc ghi log lệnh
- Giao diện hỗ trợ *Undo* bằng cách lưu lịch sử command và backup

📦 Tuy nhiên:
- Việc lưu *backup* tiêu tốn bộ nhớ
- Với trạng thái phức tạp, nên kết hợp với [Memento pattern](https://refactoring.guru/design-patterns/memento)
- Với thao tác không thể đảo ngược, có thể thực hiện thao tác ngược lại thay vì restore state
