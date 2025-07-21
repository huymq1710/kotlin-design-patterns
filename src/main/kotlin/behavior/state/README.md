# State
- Complexity: ⭐
- Popularity: ⭐⭐
## Intent

**State** là một design pattern thuộc nhóm hành vi (behavioral), cho phép một object thay đổi hành vi của nó khi internal state changes.

Trông như thể object đó đã thay đổi class.

![State Design Pattern](https://refactoring.guru/images/patterns/content/state/state-en.png)

## Problem

State pattern liên quan mật thiết đến khái niệm _Finite-State Machine_: [https://refactoring.guru/fsm](https://refactoring.guru/fsm)

![Finite-State Machine](https://refactoring.guru/images/patterns/diagrams/state/problem1.png)

Máy trạng thái hữu hạn (Finite-State Machine) có số lượng trạng thái (_states_) xác định tại một thời điểm. Mỗi trạng thái có hành vi khác nhau và chỉ chuyển đổi được theo những quy tắc (_transitions_) xác định sẵn.

Ví dụ với class `Document`, có 3 trạng thái: `Draft`, `Moderation` và `Published`. Phương thức `publish()` hoạt động khác nhau ở mỗi trạng thái:

- `Draft` → chuyển sang `Moderation`
- `Moderation` → nếu là admin thì chuyển sang `Published`
- `Published` → không làm gì cả

![Possible states of a document object](https://refactoring.guru/images/patterns/diagrams/state/problem2-en.png)

Thông thường, state machine được cài bằng nhiều câu điều kiện `if/switch`, dẫn đến code khó bảo trì khi thêm nhiều trạng thái hoặc thay đổi logic chuyển trạng thái.

## Solution

State pattern đề xuất tách mỗi trạng thái thành một class riêng biệt, chứa logic cụ thể cho trạng thái đó.

Object chính, gọi là _context_, sẽ lưu một object trạng thái và ủy quyền (delegate) toàn bộ xử lý liên quan đến trạng thái cho object đó.

![Document delegates the work to a state object](https://refactoring.guru/images/patterns/diagrams/state/solution-en.png)

Để chuyển trạng thái, context chỉ cần thay object trạng thái hiện tại bằng object trạng thái mới (tất cả đều tuân theo một interface chung).

> 🔀 So với Strategy pattern, State khác ở chỗ: các trạng thái biết đến nhau và có thể tự chuyển tiếp, còn strategy thì không.

## Real-World Analogy

📱 Các nút và thao tác trên smartphone hoạt động khác nhau tuỳ vào trạng thái:

- Đang mở khoá → thao tác bình thường
- Đang khoá → chỉ hiện màn hình mở khoá
- Sắp hết pin → chỉ hiện màn hình sạc

## Structure

![Structure of the State design pattern](https://refactoring.guru/images/patterns/diagrams/state/structure-en-indexed.png)

1. **Context**: lưu tham chiếu đến một state object cụ thể, giao tiếp thông qua interface, có thể chuyển đổi trạng thái động.
2. **State Interface**: khai báo các phương thức dùng chung cho tất cả trạng thái.
3. **Concrete States**: hiện thực cụ thể cho từng trạng thái, có thể kế thừa abstract class để tái sử dụng logic chung.
4. Trạng thái có thể giữ tham chiếu ngược về context để lấy thông tin hoặc thực hiện chuyển đổi trạng thái.

## Pseudocode

Ví dụ: media player có thể phát, tạm dừng hoặc dừng tuỳ theo trạng thái hiện tại.

![Structure of the State pattern example](https://refactoring.guru/images/patterns/diagrams/state/example.png)

⏯ State object xử lý chính logic tương tác cho player. Một số thao tác thay đổi state hiện tại để thay đổi phản ứng khi người dùng tương tác.

## Applicability

Áp dụng State pattern khi:

✅ Object có hành vi thay đổi theo trạng thái  
✅ Có nhiều trạng thái, hoặc logic trạng thái thường xuyên thay đổi  
✅ Code đang có nhiều `if`/`switch` gây khó bảo trì  
✅ Nhiều đoạn code giống nhau giữa các trạng thái

🎯 Tách code thành các class riêng biệt giúp dễ bảo trì và mở rộng, tránh trùng lặp, giảm rối rắm.

## Pros and Cons

✅ Tuân thủ _Single Responsibility Principle_: tách logic từng trạng thái  
✅ Tuân thủ _Open/Closed Principle_: thêm trạng thái mới mà không sửa class cũ  
✅ Giảm điều kiện lồng nhau trong context

⚠️ Nếu hệ thống chỉ có vài trạng thái hoặc ít thay đổi, áp dụng pattern có thể gây dư thừa

## Summary

🔁 State pattern giúp tách riêng hành vi theo từng trạng thái thành các class riêng biệt, giảm phụ thuộc vào `if/switch`.  
🔄 Các trạng thái có thể tự quyết định việc chuyển tiếp, context chỉ cần ủy quyền.  
💡 Hữu ích trong hệ thống có nhiều trạng thái, hành vi phức tạp hoặc thay đổi liên tục.
