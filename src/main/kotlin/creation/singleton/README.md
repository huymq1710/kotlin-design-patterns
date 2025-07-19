## Intent

Ensure a Java class only has one instance, and provide a global point of access to this singleton instance.

## Problem

> Chính phủ cấp hộ chiếu:
>
> Trong một quốc gia, mỗi công dân chỉ có thể được cấp một hộ chiếu hợp lệ tại một thời điểm.
Văn phòng hộ chiếu đảm bảo rằng không có hộ chiếu trùng lặp nào được cấp cho cùng một người.
Bất cứ khi nào một công dân cần đi du lịch, họ phải sử dụng hộ chiếu duy nhất này, đóng vai trò là mã định danh duy nhất được công nhận trên toàn cầu cho thông tin xác thực du lịch của họ.
> 
> Quyền truy cập được kiểm soát và quản lý phiên bản duy nhất này phản ánh cách mô hình Singleton đảm bảo quản lý đối tượng hiệu quả trong các ứng dụng.

## Solution
2 steps:

- Make the default constructor private, to prevent other objects from using the `new` operator with the Singleton class.
- Create a static creation method that acts as a constructor. Under the hood, this method calls the private constructor to create an object and saves it in a static field. All following calls to this method return the cached object.

If your code has access to the Singleton class, then it’s able to call the Singleton’s static method. So whenever that method is called, the same object is always returned.

## Structure

![Structure.png](https://refactoring.guru/images/patterns/diagrams/singleton/structure-en-1.5x.png)

## When to Use the Factory Method Pattern

* There must be exactly one instance of a class, and it must be accessible to clients from a well-known access point
* When the sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code

## Benefits and Trade-offs of Factory Method Pattern

⚠️
* Difficult to test due to global state.
* Potentially more complex lifecycle management.
* Can introduce bottlenecks if used in a concurrent context without careful synchronization.

## Real-World Applications of Singleton Pattern in Java

* The logging class
* Configuration classes in many applications
* Connection pools
* File manager
* [java.lang.Runtime#getRuntime()](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime%28%29)
* [java.awt.Desktop#getDesktop()](http://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html#getDesktop--)
* [java.lang.System#getSecurityManager()](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)
