# Iterator
- Complexity: ⭐⭐
- Popularity: ⭐⭐⭐
## Intent

**Iterator** là một mẫu thiết kế hành vi (behavioral pattern) cho phép duyệt qua các phần tử trong một collection mà không để lộ cấu trúc nội tại (danh sách, stack, cây, v.v).

![Iterator design pattern](https://refactoring.guru/images/patterns/content/iterator/iterator-en.png)

## Problem

🧩 Các collection có thể dựa trên nhiều cấu trúc khác nhau như:
- List
- Stack
- Tree
- Graph

📉 Mỗi collection cần cung cấp cách để duyệt qua phần tử, nhưng:
- Không nên trộn lẫn logic lưu trữ và duyệt
- Các thuật toán duyệt như *duyệt theo chiều sâu*, *chiều rộng*, hay *truy cập ngẫu nhiên* sẽ làm rối mã

![Various types of collections](https://refactoring.guru/images/patterns/diagrams/iterator/problem1.png)  
![Various traversal algorithms](https://refactoring.guru/images/patterns/diagrams/iterator/problem2.png)

⚠️ Nếu client code phải biết chi tiết cấu trúc thì nó sẽ bị phụ thuộc mạnh (tight coupling).

## Solution

💡 Giải pháp: tách logic duyệt ra khỏi collection bằng cách sử dụng một đối tượng gọi là *iterator*.

🎯 Mỗi *iterator*:
- Cung cấp phương thức để lấy phần tử tiếp theo
- Giữ trạng thái duyệt (vị trí hiện tại)
- Có thể hoạt động độc lập, nhiều iterator duyệt cùng một collection cùng lúc

🧩 Tất cả iterator đều implement cùng một interface → client code có thể dùng chung cho mọi kiểu collection.

![Iterators implement various traversal algorithms](https://refactoring.guru/images/patterns/diagrams/iterator/solution1.png)

## Real-World Analogy

🗺️ **Dạo chơi ở Rome**:
- Tự mò đường (đầy rủi ro 😵)
- Dùng ứng dụng chỉ đường (tiện lợi 📱)
- Thuê hướng dẫn viên (chuyên sâu 👨‍🏫)

👉 Tất cả các cách trên là các *iterator* giúp bạn "duyệt qua" danh lam thắng cảnh.

![Various ways to walk around Rome](https://refactoring.guru/images/patterns/content/iterator/iterator-comic-1-en.png)

## Structure

![Structure of the Iterator design pattern](https://refactoring.guru/images/patterns/diagrams/iterator/structure-indexed.png)

1. **Iterator Interface**: định nghĩa các phương thức duyệt (lấy phần tử tiếp theo, kiểm tra kết thúc…)
2. **Concrete Iterator**: triển khai thuật toán duyệt cụ thể, lưu trạng thái riêng
3. **Collection Interface**: cung cấp phương thức trả về iterator
4. **Concrete Collection**: triển khai collection thực tế, trả về iterator tương ứng
5. **Client**: làm việc với collection và iterator qua interface → không phụ thuộc vào class cụ thể

## Pseudocode

📱 Ví dụ: Mạng xã hội như Facebook có các loại duyệt:
- Duyệt bạn bè (`friendsIterator`)
- Duyệt đồng nghiệp (`colleaguesIterator`)

🔁 Cả hai iterator cùng implement interface chung → client có thể duyệt mà không cần biết chi tiết triển khai (REST, Auth…)

![Structure of the Iterator pattern example](https://refactoring.guru/images/patterns/diagrams/iterator/example.png)

💼 Ưu điểm:
- Dễ thay đổi mạng xã hội hoặc loại duyệt mà không cần sửa code hiện tại

## Applicability

📌 Dùng Iterator Pattern khi:
- Collection có cấu trúc phức tạp và muốn ẩn đi với client
- Muốn tái sử dụng hoặc giảm trùng lặp code duyệt
- Muốn duyệt nhiều cấu trúc khác nhau bằng chung một interface

🔒 Iterator giúp:
- Ẩn cấu trúc nội tại (tăng bảo mật và an toàn)
- Tách logic duyệt ra khỏi logic nghiệp vụ
- Hỗ trợ nhiều loại duyệt khác nhau mà không sửa code client

## Pros and Cons

✅ Ưu điểm:
- Tuân theo *Single Responsibility Principle*: tách riêng code duyệt
- Tuân theo *Open/Closed Principle*: dễ mở rộng collection/iterator mới
- Có thể duyệt song song hoặc trì hoãn duyệt
- Giao diện đơn giản cho client

⚠️ Nhược điểm:
- Có thể "overkill" nếu chỉ làm việc với collection đơn giản
- Duyệt qua iterator đôi khi kém hiệu quả hơn cách truyền thống
