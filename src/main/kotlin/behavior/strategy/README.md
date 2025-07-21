# Strategy
- Complexity: ⭐
- Popularity: ⭐⭐⭐
## Intent

🎯 **Strategy** là một behavioral design pattern cho phép bạn định nghĩa một *họ các thuật toán*, đặt mỗi thuật toán vào một class riêng và làm cho các object này có thể hoán đổi cho nhau.

![Strategy design pattern](https://refactoring.guru/images/patterns/content/strategy/strategy.png)

## Problem

🗺️ Bạn xây dựng một app điều hướng với tính năng nổi bật là bản đồ. Người dùng yêu cầu tính năng lập kế hoạch tuyến đường tự động (car, đi bộ, phương tiện công cộng...).

🚧 Tuy nhiên, việc thêm từng thuật toán routing mới làm class chính trở nên quá phức tạp và khó bảo trì. Team cũng gặp khó khăn với merge conflict khi làm việc trên cùng một class.

![The code of the navigator became very bloated](https://refactoring.guru/images/patterns/diagrams/strategy/problem.png)

## Solution

💡 Strategy pattern đề xuất tách từng thuật toán ra thành các class riêng gọi là _strategies_.

📦 Class chính (_context_) sẽ lưu tham chiếu đến một strategy cụ thể và ủy quyền việc thực thi cho strategy đó, thay vì tự xử lý.

🔄 Context không chọn strategy mà do _client_ cung cấp. Tất cả strategies đều triển khai một interface chung.

➡️ Điều này giúp context độc lập với các strategy cụ thể, dễ dàng thêm hoặc thay đổi thuật toán mà không ảnh hưởng đến các phần còn lại.

![Route planning strategies](https://refactoring.guru/images/patterns/diagrams/strategy/solution.png)

## Real-World Analogy

✈️ Bạn có thể chọn đến sân bay bằng xe buýt, taxi, hoặc xe đạp. Mỗi lựa chọn là một strategy.

![Various transportation strategies](https://refactoring.guru/images/patterns/content/strategy/strategy-comic-1-en.png)

## Structure

🧩 Cấu trúc Strategy bao gồm:

![Structure of the Strategy design pattern](https://refactoring.guru/images/patterns/diagrams/strategy/structure.png)  
![Structure of the Strategy design pattern](https://refactoring.guru/images/patterns/diagrams/strategy/structure-indexed.png)

1. **Context**: lưu trữ tham chiếu đến strategy và chỉ giao tiếp qua interface.
2. **Strategy**: định nghĩa interface chung cho tất cả strategy cụ thể.
3. **Concrete Strategies**: hiện thực thuật toán cụ thể.
4. **Client**: tạo strategy cụ thể và cung cấp cho context, có thể thay đổi tại runtime.

## Pseudocode

🧮 Ví dụ context sử dụng nhiều **strategy** để thực hiện các phép toán số học khác nhau.

## Applicability

✅ Dùng Strategy khi:
- Cần hoán đổi giữa các thuật toán khác nhau tại runtime.
- Có nhiều class tương tự nhau chỉ khác phần xử lý logic.
- Cần tách biệt logic nghiệp vụ với chi tiết thuật toán.
- Có những khối điều kiện lớn để xử lý các thuật toán khác nhau.

⚙️ Lợi ích:
- Hoán đổi thuật toán runtime dễ dàng.
- Giấu chi tiết thuật toán khỏi context.
- Giảm phụ thuộc vào kế thừa (dùng composition).
- Tuân thủ nguyên lý Open/Closed.

⚠️ Hạn chế:
- Nếu chỉ có vài thuật toán ít thay đổi, pattern này có thể gây phức tạp không cần thiết.
- Client phải hiểu rõ sự khác biệt giữa các strategy.
- Với ngôn ngữ hỗ trợ hàm lambda/closure, bạn có thể dùng function thay vì class strategy.

## How to Implement

1. Tìm thuật toán dễ thay đổi trong context.
2. Tạo strategy interface dùng chung.
3. Tách từng thuật toán thành class riêng, hiện thực interface đó.
4. Trong context, thêm thuộc tính để lưu strategy, cùng setter để thay đổi.
5. Client chọn strategy phù hợp và gán cho context.

## Pros and Cons

✅ Pros:
- 🔄 Hoán đổi thuật toán runtime.
- 🧱 Giấu chi tiết thuật toán khỏi context.
- 💡 Áp dụng composition thay vì inheritance.
- 🔓 Dễ mở rộng, không cần sửa context.

❌ Cons:
- 🧩 Phức tạp hóa chương trình với nhiều class không cần thiết.
- 🤔 Client cần biết rõ các strategy khác nhau.
- ⚙️ Có thể dùng function để đơn giản hơn nếu ngôn ngữ hỗ trợ.

## Relations with Other Patterns

🔗 Strategy có cấu trúc giống với:
- [Bridge](https://refactoring.guru/design-patterns/bridge)
- [State](https://refactoring.guru/design-patterns/state)
- [Adapter](https://refactoring.guru/design-patterns/adapter)

🆚 Strategy vs Command:
- **Command**: đại diện cho hành động, có thể lưu lịch sử, gửi từ xa, v.v.
- **Strategy**: lựa chọn thuật toán thực thi nhiệm vụ theo cách khác nhau.

🆚 Strategy vs Decorator:
- **Decorator** thay đổi _giao diện_ object.
- **Strategy** thay đổi _hành vi bên trong_.

🆚 Strategy vs Template Method:
- **Template Method** dùng kế thừa và override.
- **Strategy** dùng composition và interface, thay đổi hành vi tại runtime.

🆚 Strategy vs State:
- **State** là mở rộng của Strategy, cho phép các state biết và điều phối qua lại lẫn nhau.

## Summary

🧠 **Strategy** giúp tách thuật toán thành các class riêng biệt và hoán đổi chúng một cách linh hoạt.  
🔄 Context chỉ phụ thuộc vào interface chung, nhờ vậy dễ bảo trì, mở rộng, và test.  
⚠️ Tuy nhiên, nếu ít thuật toán hoặc không cần hoán đổi, pattern này có thể gây dư thừa.
