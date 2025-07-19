# **Behavioral Design Patterns**

> Các **Behavioral Design Patterns** tập trung vào **algorithms** và **assignment of responsibilities** giữa các đối tượng.

**Chain of Responsibility**

[![Chain of Responsibility](https://refactoring.guru/images/patterns/cards/chain-of-responsibility-mini.png)](https://refactoring.guru/design-patterns/chain-of-responsibility) 

> Cho phép bạn **truyền yêu cầu dọc theo một chuỗi các handler**. Khi nhận yêu cầu, mỗi handler quyết định sẽ **xử lý yêu cầu** hay **chuyển tiếp** nó cho handler tiếp theo trong chuỗi.

**Command**

[![Command](https://refactoring.guru/images/patterns/cards/command-mini.png)](https://refactoring.guru/design-patterns/command) 

> **Biến một yêu cầu thành một object độc lập** chứa tất cả thông tin về yêu cầu đó. Điều này cho phép bạn **truyền yêu cầu như tham số hàm**, trì hoãn hoặc xếp hàng thực thi yêu cầu và hỗ trợ các thao tác **hoàn tác**.

**Iterator**

[![Iterator](https://refactoring.guru/images/patterns/cards/iterator-mini.png)](https://refactoring.guru/design-patterns/iterator) 

> Cho phép bạn **duyệt qua các phần tử** trong một collection mà không cần tiết lộ cấu trúc bên trong của nó (danh sách, ngăn xếp, cây, v.v.).

**Mediator**

[![Mediator](https://refactoring.guru/images/patterns/cards/mediator-mini.png)](https://refactoring.guru/design-patterns/mediator)

> Giảm thiểu sự phụ thuộc **hỗn loạn** giữa các đối tượng. Mẫu này **hạn chế giao tiếp trực tiếp** giữa các đối tượng và buộc chúng chỉ hợp tác qua một đối tượng **mediator**.

**Memento**

[![Memento](https://refactoring.guru/images/patterns/cards/memento-mini.png)](https://refactoring.guru/design-patterns/memento) 

> Cho phép bạn **lưu trữ và khôi phục trạng thái trước đó** của một đối tượng mà không tiết lộ chi tiết về cách triển khai của nó.

**Observer**

[![Observer](https://refactoring.guru/images/patterns/cards/observer-mini.png)](https://refactoring.guru/design-patterns/observer) 

> Cho phép bạn **định nghĩa cơ chế đăng ký** để thông báo nhiều đối tượng về bất kỳ sự kiện nào xảy ra với đối tượng mà chúng đang quan sát.

**State**

[![State](https://refactoring.guru/images/patterns/cards/state-mini.png)](https://refactoring.guru/design-patterns/state) 

> Cho phép một đối tượng thay đổi hành vi của nó khi **trạng thái nội bộ thay đổi**. Nó sẽ giống như thể đối tượng đã thay đổi **lớp** của mình.

**Strategy**

[![Strategy](https://refactoring.guru/images/patterns/cards/strategy-mini.png)](https://refactoring.guru/design-patterns/strategy) 

> Cho phép bạn **định nghĩa một họ thuật toán**, đưa mỗi thuật toán vào một lớp riêng biệt và làm cho các đối tượng của chúng có thể thay thế cho nhau.

**Template Method**

[![Template Method](https://refactoring.guru/images/patterns/cards/template-method-mini.png)](https://refactoring.guru/design-patterns/template-method) 

> Định nghĩa **dàn bài thuật toán** trong lớp cha nhưng cho phép các lớp con **ghi đè** các bước cụ thể của thuật toán mà không thay đổi cấu trúc của nó.

**Visitor**

[![Visitor](https://refactoring.guru/images/patterns/cards/visitor-mini.png)](https://refactoring.guru/design-patterns/visitor) 

> Cho phép bạn **tách rời các thuật toán** khỏi các đối tượng mà chúng thao tác.
