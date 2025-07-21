# Memento

🔁 **Còn gọi là: Snapshot**

- Complexity: ⭐⭐⭐
- Popularity: ⭐

## Intent

**Memento** là một mẫu thiết kế hành vi cho phép bạn lưu và khôi phục trạng thái trước đó của một đối tượng mà không làm lộ chi tiết triển khai bên trong của nó.

🧠 **Ý tưởng:** Cho phép lưu trữ trạng thái đối tượng như một bản sao, và có thể khôi phục lại sau này — mà không vi phạm tính đóng gói.

![Memento design pattern](https://refactoring.guru/images/patterns/content/memento/memento-en.png)

---

## Problem

✏️ Hãy tưởng tượng bạn đang xây dựng một trình soạn thảo văn bản có tính năng hoàn tác (undo). Bạn muốn lưu lại toàn bộ trạng thái đối tượng trước mỗi thao tác để có thể quay lại trạng thái trước đó khi cần.

❗ Nhưng nếu lưu trạng thái bằng cách truy cập toàn bộ dữ liệu bên trong các đối tượng (bao gồm cả trường `private`) thì sẽ vi phạm tính đóng gói. Hơn nữa, nếu cấu trúc lớp thay đổi thì logic sao chép trạng thái cũng phải thay đổi theo — điều này làm mã dễ bị lỗi và khó bảo trì.

![Reverting operations in the editor](https://refactoring.guru/images/patterns/diagrams/memento/problem1-en.png)

![How to make a copy of the object's private state?](https://refactoring.guru/images/patterns/diagrams/memento/problem2-en.png)

---

## Solution

✅ **Giải pháp:** Giao nhiệm vụ tạo bản sao trạng thái cho chính đối tượng đang sở hữu nó (gọi là _originator_). Đối tượng này tạo ra một đối tượng _memento_ để lưu trữ trạng thái của chính nó.

🔒 Memento chỉ có thể được đọc/ghi bởi originator. Các đối tượng khác (gọi là _caretaker_) chỉ có thể lưu trữ hoặc truyền memento, không thể truy cập vào dữ liệu bên trong.

🧾 Lịch sử chỉnh sửa có thể được lưu trữ dưới dạng một ngăn xếp (stack) các memento. Khi undo, caretaker trả về memento gần nhất để originator khôi phục lại trạng thái.

![The originator has full access to the memento, whereas the caretaker can only access the metadata](https://refactoring.guru/images/patterns/diagrams/memento/solution-en.png)

---

## Structure

### Implementation based on nested classes

🧩 Cài đặt bằng **lớp lồng nhau** (nested class) trong ngôn ngữ như Java/C++ cho phép originator truy cập vào dữ liệu private trong memento, còn caretaker thì không.

![Memento based on nested classes](https://refactoring.guru/images/patterns/diagrams/memento/structure1-indexed.png)

1. **Originator**: tạo và phục hồi trạng thái từ memento.
2. **Memento**: chứa dữ liệu trạng thái, thường bất biến (immutable).
3. **Caretaker**: biết khi nào cần lưu và phục hồi trạng thái, nhưng không can thiệp vào nội dung memento.
4. **Memento** được lồng trong originator nên chỉ originator có thể truy cập dữ liệu thật sự.

---

### Implementation based on an intermediate interface

📜 Dùng **interface trung gian** nếu ngôn ngữ không hỗ trợ lớp lồng nhau (ví dụ: PHP).

![Memento without nested classes](https://refactoring.guru/images/patterns/diagrams/memento/structure2-indexed.png)

1. Caretaker chỉ tương tác với memento qua interface giới hạn (chứa metadata).
2. Originator truy cập đầy đủ dữ liệu trong memento, nhưng phải khai báo các thành viên công khai.

---

### Implementation with even stricter encapsulation

🔐 Cài đặt với **đóng gói nghiêm ngặt hơn** — thậm chí caretaker không biết gì về originator.

![Memento with strict encapsulation](https://refactoring.guru/images/patterns/diagrams/memento/structure3-indexed.png)

1. Mỗi memento được liên kết với một originator cụ thể.
2. Caretaker hoàn toàn không thể chỉnh sửa memento.
3. Memento phục hồi trạng thái originator thông qua các phương thức setter nội bộ.

---

## Pseudocode

👨‍💻 Ví dụ sử dụng **Memento** kết hợp với **Command** để lưu lại trạng thái phức tạp của trình soạn thảo văn bản.

![Structure of the Memento example](https://refactoring.guru/images/patterns/diagrams/memento/example.png)

- Lệnh (Command) hoạt động như caretaker, lưu memento trước khi thực hiện hành động.
- Memento không có setter hay getter công khai, chỉ originator có thể phục hồi lại trạng thái.
- Hỗ trợ nhiều cửa sổ editor độc lập với ngăn xếp undo riêng biệt.

---

## Applicability

🎯 Sử dụng Memento khi:

- Cần **khôi phục trạng thái trước đó** của đối tượng (ví dụ: undo, rollback).
- Tránh vi phạm **tính đóng gói** khi lưu trạng thái đối tượng.
- Hữu ích trong các hệ thống **giao dịch**, cho phép rollback khi có lỗi.

---

## Pros and Cons

✅ **Ưu điểm:**
- Lưu trạng thái mà không vi phạm đóng gói.
- Caretaker quản lý lịch sử, làm code originator đơn giản hơn.

⚠️ **Nhược điểm:**
- Tốn RAM nếu tạo memento quá thường xuyên.
- Cần dọn dẹp memento lỗi thời để tránh rò rỉ bộ nhớ.
- Một số ngôn ngữ động như PHP/Python/JS khó bảo vệ trạng thái memento khỏi bị thay đổi.

---

## Relations with Other Patterns 🔗

-   Bạn có thể kết hợp [Command](https://refactoring.guru/design-patterns/command) và [Memento](https://refactoring.guru/design-patterns/memento) để triển khai tính năng “hoàn tác” (undo). Trong trường hợp này, **Command** thực hiện các thao tác trên đối tượng, còn **Memento** lưu trạng thái của đối tượng trước khi lệnh được thực thi.

-   Bạn có thể dùng [Memento](https://refactoring.guru/design-patterns/memento) cùng với [Iterator](https://refactoring.guru/design-patterns/iterator) để **lưu lại trạng thái vòng lặp hiện tại** và **khôi phục lại nếu cần**.

-   Đôi khi [Prototype](https://refactoring.guru/design-patterns/prototype) có thể là một giải pháp **đơn giản hơn** so với Memento — đặc biệt khi đối tượng cần lưu trạng thái khá đơn giản và không có liên kết phức tạp với tài nguyên bên ngoài (hoặc các liên kết này có thể tái thiết lập dễ dàng).

## Summary

🧠 **Memento** cho phép lưu trữ và khôi phục trạng thái của đối tượng mà không vi phạm đóng gói.  
🔁 Phù hợp cho các tính năng như **undo**, **rollback**, hoặc lưu trạng thái tạm thời.  
🧰 Có thể kết hợp hiệu quả với **Command**, **Iterator**, hoặc thay thế bằng **Prototype** khi đối tượng đơn giản.
