# **Proxy**

- Complexity: ⭐⭐
- Popularity: ⭐

## **Intent**

**Proxy** là một *structural design pattern* cho phép bạn cung cấp một **đại diện (substitute)** hoặc **vật giữ chỗ (placeholder)** cho một object khác. Proxy **kiểm soát quyền truy cập** đến object gốc và có thể thực hiện hành động *trước hoặc sau* khi lời gọi được chuyển đến object đó.

![Proxy design pattern](https://refactoring.guru/images/patterns/content/proxy/proxy.png)

---

## **Problem**

Tại sao cần kiểm soát truy cập?

Ví dụ: bạn có một object rất lớn, tốn nhiều **tài nguyên hệ thống**, nhưng chỉ **thỉnh thoảng mới cần dùng**.

![Problem solved by Proxy pattern](https://refactoring.guru/images/patterns/diagrams/proxy/problem-en.png)

* Việc **khởi tạo trì hoãn (lazy initialization)** là cần thiết.
* Nhưng nếu bạn lặp lại mã khởi tạo ở mọi nơi dùng object đó → **trùng lặp code**.
* Không thể tích hợp logic này vào class gốc nếu đó là **thư viện bên thứ 3** không sửa được.

---

## **Solution**

**Proxy pattern** đề xuất:

* Tạo một *proxy class* có **interface giống hệt** service gốc.
* Client sử dụng proxy thay vì object thật.
* Proxy sẽ **khởi tạo object thật khi cần**, và **ủy quyền công việc** cho nó.

![Solution with the Proxy pattern](https://refactoring.guru/images/patterns/diagrams/proxy/solution-en.png)

Ưu điểm:

* Có thể xử lý việc **khởi tạo, cache, log, kiểm soát truy cập...** mà không thay đổi object thật.
* Do **cùng interface**, client không nhận ra sự khác biệt giữa *proxy* và *real object*.

---

## **Real-World Analogy**

![A credit card is a proxy for a bundle of cash](https://refactoring.guru/images/patterns/diagrams/proxy/live-example.png)

**Thẻ tín dụng** là một **proxy** cho **tài khoản ngân hàng**, còn tài khoản là proxy cho **tiền mặt**.

→ Mọi thứ đều dùng được để **thanh toán**, tức là cùng interface. Cả khách hàng và người bán đều được lợi mà không cần trực tiếp xử lý tiền mặt.

---

## **Applicability**

Các trường hợp ứng dụng phổ biến của **Proxy**:

* **Lazy Initialization (Virtual Proxy):** Trì hoãn khởi tạo object tốn tài nguyên cho đến khi thực sự cần.

* **Access Control (Protection Proxy):** Kiểm soát quyền truy cập; ví dụ: chỉ client hợp lệ mới truy cập được.

* **Remote Proxy:** Đại diện cho object nằm trên **server từ xa**; xử lý việc giao tiếp qua mạng.

* **Logging Proxy:** Ghi lại **lịch sử truy cập** vào service object.

* **Caching Proxy:** Lưu **kết quả truy vấn**, tránh xử lý lại các request giống nhau.

* **Smart Reference:** Theo dõi và **giải phóng object** nặng nếu không còn client sử dụng.

---

## **Pros and Cons**

**✅ Ưu điểm:**

* Kiểm soát service object **mà client không biết**.
* Quản lý **vòng đời** của object.
* Làm việc ngay cả khi object chưa sẵn sàng.
* Tuân theo **Open/Closed Principle**: thêm proxy mới mà không cần sửa code gốc.

**⚠️ Nhược điểm:**

* **Tăng độ phức tạp** do phải thêm nhiều class.
* Có thể **làm chậm phản hồi** nếu proxy xử lý nhiều logic trung gian.

## Structure

![Structure of the Proxy design pattern](https://refactoring.guru/images/patterns/diagrams/proxy/structure.png)

## Pseudocode

This example illustrates how the **Proxy** pattern can help to introduce lazy initialization and caching to a 3rd-party YouTube integration library.

![Structure of the Proxy pattern example](https://refactoring.guru/images/patterns/diagrams/proxy/example.png)


Thư viện cung cấp cho chúng ta một **class tải video**. Tuy nhiên, nó rất **kém hiệu quả**. Nếu ứng dụng client yêu cầu tải cùng một video nhiều lần, thư viện sẽ **tải lại video** đó mỗi lần, thay vì **cache và tái sử dụng** file đã tải lần đầu.

Class **proxy** triển khai **cùng interface** như downloader gốc và **ủy thác** tất cả công việc cho nó. Tuy nhiên, proxy **theo dõi các file đã tải** và trả về kết quả đã **cache** khi ứng dụng yêu cầu video đã tải trước đó.
