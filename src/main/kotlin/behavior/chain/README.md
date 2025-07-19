# Chain of Responsibility

Also known as: CoR, Chain of Command
- Complexity: ⭐⭐
- Popularity: ⭐⭐

## Intent

**Chain of Responsibility** là một mẫu thiết kế hành vi (behavioral design pattern) cho phép truyền yêu cầu qua một chuỗi các *handler* (bộ xử lý). Mỗi handler có thể xử lý yêu cầu hoặc chuyển tiếp nó cho handler tiếp theo trong chuỗi.

![Chain of Responsibility design pattern](https://refactoring.guru/images/patterns/content/chain-of-responsibility/chain-of-responsibility.png)

## Problem

🎯 Bài toán trong một hệ thống đặt hàng trực tuyến, bạn cần kiểm tra theo thứ tự:
- ✅ Xác thực người dùng
- 🛡️ Phân quyền quản trị
- 🧼 Kiểm tra và làm sạch dữ liệu đầu vào
- 🚫 Chặn brute-force
- ♻️ Trả về kết quả từ bộ nhớ đệm nếu có

Mỗi lần thêm logic kiểm tra lại khiến mã nguồn trở nên phức tạp, cồng kềnh, khó bảo trì và tái sử dụng.

![Problem, solved by Chain of Responsibility](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/problem1-en.png)

📉 Mã càng ngày càng rối khi thêm nhiều lớp kiểm tra.

![With each new check the code became bigger, messier, and uglier](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/problem2-en.png)

## Solution

💡 Giải pháp: Biến từng hành vi kiểm tra thành một đối tượng riêng gọi là `handle`*. 

Mỗi `handler` thực hiện xử lý rồi chuyển tiếp yêu cầu tới handler tiếp theo trong chuỗi (nếu cần).

✨ Ưu điểm:
- Dễ mở rộng: thêm, bớt handler
- Dễ tái sử dụng: handler độc lập
- Có thể dừng xử lý tại bất kỳ handler nào

![Handlers are lined-up one by one, forming a chain](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/solution1-en.png)

🎮 GUI là ví dụ điển hình: sự kiện chuột sẽ đi qua chuỗi các phần tử giao diện đến khi có phần tử xử lý được.

![A chain can be formed from a branch of an object tree](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/solution2-en.png)

🔁 Điều quan trọng là tất cả các handler phải implement cùng một interface để có thể liên kết chuỗi một cách linh hoạt.

## Real-World Analogy

📞 Khi gọi hỗ trợ kỹ thuật:
- 🤖 Gặp trả lời tự động trước
- 👨‍💼 Được chuyển tới tổng đài viên
- 👨‍🔧 Cuối cùng gặp kỹ sư xử lý chuyên sâu

Tương tự, mỗi yêu cầu sẽ đi qua chuỗi xử lý đến khi có người/đối tượng phù hợp xử lý nó.

![Talking with tech support can be hard](https://refactoring.guru/images/patterns/content/chain-of-responsibility/chain-of-responsibility-comic-1-en.png)

## Pseudocode

📋 Ví dụ minh họa bằng GUI:
- GUI sử dụng Composite pattern
- Mỗi thành phần GUI có thể chứa các thành phần con
- Khi người dùng nhấn F1, yêu cầu *help* sẽ đi từ phần tử nhỏ nhất lên cha nó đến khi có phần tử nào xử lý được yêu cầu

![Structure of the Chain of Responsibility example](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/example-en.png)

🧩 Mỗi thành phần có thể hiển thị *tooltip* hoặc *mở trang trợ giúp* tùy cách xử lý riêng

![Structure of the Chain of Responsibility example](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/example2-en.png)

## Applicability

🎯 Áp dụng Chain of Responsibility khi:
- Chương trình cần xử lý các yêu cầu theo nhiều cách khác nhau nhưng chưa biết trước loại và thứ tự
- Cần thực hiện một chuỗi xử lý tuần tự, có thể thay đổi thứ tự động
- Muốn dễ dàng thêm, bớt hoặc thay đổi thứ tự các handler tại runtime

🔗 Ưu điểm nổi bật: kết nối các handler thành chuỗi linh hoạt, có thể xử lý theo đúng kế hoạch mà không phụ thuộc vào từng lớp cụ thể.
