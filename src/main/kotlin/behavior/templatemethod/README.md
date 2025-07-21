# Template Method
- Complexity: ⭐
- Popularity: ⭐⭐
## Intent

**Template Method** là một pattern thuộc nhóm hành vi (behavioral), cho phép định nghĩa bộ khung (skeleton) của một thuật toán trong lớp cha, nhưng để cho các lớp con ghi đè (override) một số bước nhất định mà không làm thay đổi cấu trúc tổng thể của thuật toán.

![Template method design pattern](https://refactoring.guru/images/patterns/content/template-method/template-method.png)

## Problem

Ứng dụng data mining, phân tích các định dạng tài liệu khác nhau như PDF, DOC, CSV. Ban đầu chỉ hỗ trợ DOC, sau đó mở rộng thêm CSV và PDF.

![Data mining classes contained a lot of duplicate code](https://refactoring.guru/images/patterns/diagrams/template-method/problem.png)

🔁 Mỗi class xử lý định dạng khác nhau nhưng phần xử lý và phân tích dữ liệu thì giống nhau ⇒ gây trùng lặp code.  
😵‍💫 Ngoài ra, code client phải dùng nhiều điều kiện để xử lý theo từng class ⇒ cần base class chung để dùng tính đa hình (polymorphism).

## Solution

Pattern Template Method đề xuất chia nhỏ thuật toán thành từng bước, đóng gói chúng thành các method, rồi gọi chuỗi các method đó trong một _template method_.  
🔧 Các bước có thể là `abstract`, hoặc có default implementation.

📌 Template Method sẽ:
- Cho phép subclass cài đặt các bước riêng biệt
- Không cho phép override toàn bộ phương thức thuật toán

![Template method defines the skeleton of the algorithm](https://refactoring.guru/images/patterns/diagrams/template-method/solution-en.png)

👣 Chia bước thành:
- `abstract steps`: bắt buộc subclass phải override
- `optional steps`: có default nhưng subclass vẫn có thể ghi đè
- `hooks`: method rỗng, subclass override hay không đều được → thêm điểm mở rộng

## Real-World Analogy

![Mass housing construction](https://refactoring.guru/images/patterns/diagrams/template-method/live-example.png)

🏗 Mẫu thiết kế nhà có thể có các điểm mở rộng cho phép điều chỉnh phù hợp với yêu cầu chủ nhà.  
📐 Các bước xây dựng như nền móng, khung nhà, lắp điện nước... có thể tùy chỉnh một phần nhưng vẫn theo bộ khung chung.

## Structure

![Structure of the Template Method design pattern](https://refactoring.guru/images/patterns/diagrams/template-method/structure-indexed.png)

1. **Abstract Class**: định nghĩa các bước và template method gọi các bước theo thứ tự. Các bước có thể là `abstract` hoặc có sẵn mặc định.
2. **Concrete Class**: ghi đè các bước cụ thể nhưng không ghi đè template method.

## Pseudocode

Ví dụ Template Method trong game chiến thuật đơn giản để xác định hành vi của AI.

![Structure of the Template Method pattern example](https://refactoring.guru/images/patterns/diagrams/template-method/example.png)

🤖 Mỗi chủng tộc (orc, human, monster...) có hành vi khác nhau nhưng cùng chung khung hành vi AI.  
🔄 Ghi đè từng bước giúp thay đổi đặc điểm chiến thuật riêng (tấn công, phòng thủ, không xây dựng...).

## Applicability

✅ Dùng Template Method khi:
- Muốn client mở rộng một số bước của thuật toán, không phải toàn bộ
- Có nhiều class dùng chung thuật toán nhưng khác biệt nhỏ → dễ bảo trì hơn

🧹 Chuyển thuật toán phức tạp thành từng bước nhỏ → dễ override, giảm trùng lặp code.

## Pros and Cons

### ✅ Ưu điểm
- Cho phép override các phần cụ thể của thuật toán
- Kéo các phần code trùng vào superclass

### ❌ Nhược điểm
- Client có thể bị hạn chế bởi khung thuật toán có sẵn
- Có thể vi phạm _Liskov Substitution Principle_ nếu subclass loại bỏ step mặc định
- Template method có nhiều bước thì khó maintain hơn

## Relations with Other Patterns

- [Factory Method](https://refactoring.guru/design-patterns/factory-method) là phiên bản cụ thể của Template Method.
- Template Method dùng kế thừa để thay đổi hành vi thuật toán ở **cấp lớp** (static).
- [Strategy](https://refactoring.guru/design-patterns/strategy) dùng composition để thay đổi hành vi ở **cấp đối tượng** (dynamic).

---

## Summary

📄 Template Method cho phép bạn xác định bộ khung của một thuật toán, và để các lớp con tùy biến một số bước cụ thể.  
🔧 Giúp giảm lặp code, tăng khả năng mở rộng, đặc biệt khi có nhiều class dùng chung cấu trúc xử lý.  
🏗 Phù hợp với quy trình có bước cố định nhưng cho phép tùy biến từng phần nhỏ như xây dựng nhà, AI game, phân tích dữ liệu...
