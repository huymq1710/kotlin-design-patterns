# **Flyweight**
*Còn được gọi là: Cache*

- Complexity: ⭐⭐⭐
- Popularity: ⭐

## **Intent**

**Flyweight** là một *structural design pattern* giúp bạn nhồi nhét được **nhiều object hơn vào RAM**, bằng cách **chia sẻ phần trạng thái chung** giữa nhiều object thay vì lưu toàn bộ dữ liệu trong từng object riêng lẻ.

![Flyweight design pattern](https://refactoring.guru/images/patterns/content/flyweight/flyweight.png)

---

## **Problem**

Bạn xây một game bắn súng với **particle system** sống động (đạn, tên lửa, mảnh vụn...). Trên máy bạn thì chạy tốt, nhưng khi test trên máy bạn bè (cấu hình yếu hơn), game **liên tục bị crash do thiếu RAM**.

Lý do là mỗi *particle* được đại diện bởi một *object* chứa nhiều dữ liệu lặp lại (ví dụ: màu sắc và hình ảnh giống nhau cho mọi viên đạn), khiến RAM bị tiêu tốn quá mức.

![Flyweight pattern problem](https://refactoring.guru/images/patterns/diagrams/flyweight/problem-en.png)

---

## **Solution**

Xét lại *class `Particle`*, ta thấy:

* **Intrinsic state** (trạng thái nội tại): như `color`, `sprite` → giống nhau giữa nhiều particle
* **Extrinsic state** (trạng thái ngoại tại): như `position`, `vector`, `speed` → khác nhau, thay đổi theo thời gian

**Flyweight pattern** đề xuất:

* Chỉ lưu *intrinsic state* bên trong object.
* *Extrinsic state* được truyền vào khi cần, thông qua phương thức.

→ Kết quả: chỉ cần 3 *Flyweight object* đại diện cho bullet, missile, và shrapnel.

![Flyweight pattern solution](https://refactoring.guru/images/patterns/diagrams/flyweight/solution1-en.png)
![Flyweight pattern solution](https://refactoring.guru/images/patterns/diagrams/flyweight/solution3-en.png)

---

### **Extrinsic state storage**

*Extrinsic state* sẽ được lưu ở đâu?

* Thường được chuyển vào một class *container* (ví dụ: class `Game`)
* Dữ liệu như `coordinates`, `vectors`, `speed` được lưu trong các *array riêng biệt* và đồng bộ qua chỉ số index.
* Một giải pháp tốt hơn là dùng một *context class* để chứa toàn bộ *extrinsic state* + tham chiếu tới *flyweight*.

![Flyweight pattern solution](https://refactoring.guru/images/patterns/diagrams/flyweight/solution2-en.png)

→ Mặc dù vẫn cần nhiều *context object*, nhưng mỗi cái giờ đây **nhẹ hơn rất nhiều** vì không còn chứa dữ liệu nặng như trước.

---

### **Flyweight và tính bất biến (immutability)**

Vì một *flyweight* được dùng chung ở nhiều ngữ cảnh → nó cần **bất biến**: chỉ khởi tạo 1 lần qua constructor, **không có setter hoặc field public**.

---

### **Flyweight Factory**

Để quản lý các *flyweight object*, có thể dùng một **factory method**:

* Nhận *intrinsic state*
* Trả về flyweight tương ứng (nếu đã có thì tái sử dụng, nếu chưa thì tạo mới và lưu vào pool)

Có thể đặt method này ở:

* *Flyweight container*
* Một *factory class* riêng
* Hoặc như một method static trong *flyweight class*

---

## **Applicability**

Sử dụng **Flyweight pattern** khi:

* Chương trình cần tạo **số lượng lớn object tương tự nhau**
* **RAM bị giới hạn**, không đủ chứa tất cả object
* Các object có nhiều phần **trạng thái trùng lặp**, có thể **chia sẻ** được

---

## **Pros and Cons**

**✅ Ưu điểm:**

* Giảm đáng kể mức sử dụng **RAM** nếu có nhiều object tương tự nhau

**⚠️ Nhược điểm:**

* Có thể **tăng chi phí CPU**, do phải truyền lại *extrinsic state* liên tục
* **Code phức tạp hơn**, gây khó hiểu cho người mới vì trạng thái bị tách ra nhiều nơi

## **Structure**

![](https://refactoring.guru/images/patterns/diagrams/flyweight/structure-1.5x.png)

## **Pseudocode**

In this example, the **Flyweight** pattern helps to reduce memory usage when rendering millions of tree objects on a canvas.

![Flyweight pattern example](https://refactoring.guru/images/patterns/diagrams/flyweight/example.png)
