# Mediator Pattern

Còn được gọi là: Trung gian, Điều phối viên, Bộ điều khiển trung tâm

- Complexity: ⭐⭐
- Popularity: ⭐⭐

## Ý tưởng chính

**Mediator** là một mẫu thiết kế hành vi (behavioral pattern) giúp giảm sự phụ thuộc lẫn nhau giữa các đối tượng. Thay vì các đối tượng giao tiếp trực tiếp, chúng sẽ tương tác thông qua một *đối tượng trung gian* gọi là Mediator.

![Mediator design pattern](https://refactoring.guru/images/patterns/content/mediator/mediator.png)

---

## Vấn đề

💥 Khi ứng dụng ngày càng phức tạp, các thành phần giao diện người dùng (UI) như: nút, hộp thoại, ô nhập liệu... thường phải tương tác với nhau.

Ví dụ: Trong form chỉnh sửa thông tin khách hàng:
- Nếu chọn checkbox “Tôi có chó” → hiển thị thêm ô nhập tên chó.
- Khi bấm “Submit” → phải kiểm tra toàn bộ dữ liệu hợp lệ trước khi gửi.

Nếu để các thành phần này tự giao tiếp với nhau, thì chúng sẽ trở nên gắn chặt, khó tái sử dụng và khó bảo trì.

![UI elements are interdependent](https://refactoring.guru/images/patterns/diagrams/mediator/problem2.png)

---

## Giải pháp

Mediator đề xuất rằng **các thành phần không nên giao tiếp trực tiếp với nhau**, mà thay vào đó:
- **Mỗi thành phần chỉ biết tới Mediator**
- Mediator sẽ quyết định việc chuyển tiếp sự kiện tới thành phần nào

✨ Nhờ đó:
- Giảm sự phụ thuộc giữa các thành phần
- Dễ tái sử dụng và thay đổi độc lập
- Giao tiếp rõ ràng, tập trung ở một nơi

![UI elements should communicate via the mediator](https://refactoring.guru/images/patterns/diagrams/mediator/solution1-en.png)

---

## Ví dụ thực tế

✈️ **Tháp điều khiển sân bay**

- Máy bay không tự giao tiếp với nhau để phân quyền hạ cánh
- Tất cả liên lạc đều thông qua tháp điều khiển
- Nếu không, phi công phải biết mọi máy bay xung quanh → quá phức tạp và nguy hiểm

![Air traffic control tower](https://refactoring.guru/images/patterns/diagrams/mediator/live-example.png)

---

## Cấu trúc

![Structure of Mediator](https://refactoring.guru/images/patterns/diagrams/mediator/structure.png)

1. **Component**: Thành phần cụ thể (nút, ô nhập, v.v.) — chỉ biết đến interface Mediator
2. **Mediator Interface**: Khai báo phương thức giao tiếp (ví dụ `notify(sender, event)`)
3. **Concrete Mediator**: Triển khai chi tiết luồng xử lý giữa các Component
4. Component không biết ai sẽ xử lý tiếp — chỉ gửi thông điệp đến Mediator

🧩 Mọi tương tác đều đi qua Mediator → các component không bị ràng buộc với nhau.

---

## Pseudo code

Trong ví dụ này, mẫu **Mediator** giúp bạn loại bỏ sự phụ thuộc lẫn nhau 
giữa nhiều lớp UI khác nhau: nút, hộp kiểm và nhãn văn bản.

![](https://refactoring.guru/images/patterns/diagrams/mediator/example-1.5x.png)

Một phần tử, được kích hoạt bởi người dùng, không giao tiếp trực tiếp với các phần tử khác, 
ngay cả khi trông có vẻ như nó được thiết kế để giao tiếp. 
Thay vào đó, phần tử chỉ cần cho đối tượng trung gian của nó biết về sự kiện, 
truyền bất kỳ thông tin ngữ cảnh nào cùng với thông báo đó.


## Khi nào sử dụng Mediator?

✅ Sử dụng khi:

-   Các lớp có quá nhiều mối quan hệ phức tạp → khó quản lý

-   Muốn giảm phụ thuộc giữa các thành phần

-   Muốn tái sử dụng component ở môi trường khác


* * *

## Ưu điểm

✔ Tuân theo **Single Responsibility**: tách giao tiếp khỏi component  
✔ Tuân theo **Open/Closed**: có thể mở rộng mediator mà không sửa các component  
✔ Component dễ tái sử dụng hơn  
✔ Dễ kiểm soát luồng giao tiếp

* * *

## Nhược điểm

❗ Mediator có thể trở thành **God Object** nếu chứa quá nhiều logic điều phối

* * *

## Tổng kết

🔁 Mediator giúp bạn điều phối nhiều component mà không khiến chúng lệ thuộc vào nhau.  
💡 Nó phù hợp với ứng dụng GUI, hệ thống phức tạp, trò chơi, trình quản lý workflow...
