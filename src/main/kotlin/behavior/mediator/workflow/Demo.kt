package behavior.mediator.workflow

import behavior.mediator.workflow.components.impl.*
import behavior.mediator.workflow.mediator.impl.WorkflowManager

fun main() {
    val workflowManager = WorkflowManager()

    // Tạo các thành phần workflow
    val submitter = RequestSubmitter("Huy Mac")
    val supervisor = SupervisorApprover("Quản lý trực tiếp")
    val hrManager = HRManager("Phòng nhân sự")
    val director = DirectorApprover("Giám đốc")
    val notificationSystem = NotificationSystem()
    val auditLogger = AuditLogger()
    val dashboard = WorkflowDashboard()

    // Đăng ký các thành phần với mediator
    workflowManager.registerComponent(submitter)
    workflowManager.registerComponent(supervisor)
    workflowManager.registerComponent(hrManager)
    workflowManager.registerComponent(director)
    workflowManager.registerComponent(notificationSystem)
    workflowManager.registerComponent(auditLogger)
    workflowManager.registerComponent(dashboard)

    // Demo workflow
    println("=== DEMO HỆ THỐNG QUẢN LÝ WORKFLOW ===\n")

    // Tạo đơn xin nghỉ phép
    val leaveRequest = LeaveRequest(
        id = "REQ-001",
        employeeId = "EMP-123",
        employeeName = "Nguyễn Văn A",
        leaveType = LeaveType.ANNUAL,
        startDate = "2024-08-01",
        endDate = "2024-08-03",
        reason = "Nghỉ phép năm"
    )

    // Bắt đầu workflow
    submitter.submitRequest(leaveRequest)

    Thread.sleep(1000)

    // Giám sát viên phê duyệt
    supervisor.processRequest("REQ-001", ApprovalDecision.APPROVED, "Đồng ý cho nghỉ")

    Thread.sleep(1000)

    // HR xử lý
    hrManager.processRequest("REQ-001", ApprovalDecision.APPROVED, "Đã kiểm tra số ngày phép còn lại")

    Thread.sleep(1000)

    // Giám đốc phê duyệt cuối cùng
    director.processRequest("REQ-001", ApprovalDecision.APPROVED, "Chấp thuận")

    Thread.sleep(1000)

    // Hiển thị báo cáo cuối
    dashboard.showSummary()
}


/**
 * Sample output:
 *
 * ✅ Đăng ký thành phần: Request Submitter (submitter_Huy_Mac)
 * ✅ Đăng ký thành phần: Supervisor (supervisor)
 * ✅ Đăng ký thành phần: HR Manager (hr)
 * ✅ Đăng ký thành phần: Director (director)
 * ✅ Đăng ký thành phần: Notification System (notification_system)
 * ✅ Đăng ký thành phần: Audit Logger (audit_logger)
 * ✅ Đăng ký thành phần: Workflow Dashboard (dashboard)
 * === DEMO HỆ THỐNG QUẢN LÝ WORKFLOW ===
 *
 * 📝 Huy Mac gửi đơn xin nghỉ phép: REQ-001
 *    - Loại: Nghỉ phép năm
 *    - Từ: 2024-08-01 đến 2024-08-03
 *    - Lý do: Nghỉ phép năm
 * 📋 LOG: Đơn REQ-001 được gửi bởi Nguyễn Văn A
 * 📊 Dashboard cập nhật: REQ-001 -> Chờ xử lý
 * 📊 Tiến độ workflow: 25.0%
 * 🔔 Thông báo gửi đến supervisor: Có đơn xin nghỉ phép mới cần xem xét: REQ-001
 * 👤 Quản lý trực tiếp xem xét đơn REQ-001
 *    - Quyết định: Chấp thuận
 *    - Nhận xét: Đồng ý cho nghỉ
 * 📊 Tiến độ workflow cho REQ-001: 50.0%
 * 🔄 Các bước còn lại: Giám đốc xem xét → Hoàn thành
 * 🔔 Thông báo gửi đến hr: Đơn REQ-001 cần xem xét từ HR
 * 📋 LOG: Đơn REQ-001 được xử lý bởi supervisor - Kết quả: APPROVED
 * 🏢 Phòng nhân sự xử lý đơn REQ-001
 *    - Kiểm tra chính sách công ty
 *    - Quyết định: Chấp thuận
 *    - Nhận xét: Đã kiểm tra số ngày phép còn lại
 * 📊 Tiến độ workflow cho REQ-001: 75.0%
 * 🔄 Các bước còn lại: Hoàn thành
 * 🔔 Thông báo gửi đến director: Đơn REQ-001 cần phê duyệt từ Giám đốc
 * 📋 LOG: Đơn REQ-001 được xử lý bởi hr - Kết quả: APPROVED
 * ⭐ Giám đốc phê duyệt cuối cùng cho đơn REQ-001
 *    - Quyết định: Chấp thuận
 *    - Nhận xét: Chấp thuận
 * 📊 Tiến độ workflow cho REQ-001: 100.0%
 * 📊 Dashboard cập nhật: REQ-001 -> Hoàn thành
 * 📧 Thông báo gửi đến EMP-123: Đơn REQ-001 đã được phê duyệt hoàn tất
 * 📋 LOG: Đơn REQ-001 hoàn thành workflow
 * 🎉 Workflow hoàn thành cho đơn: REQ-001 (100% hoàn thành)
 * 📋 LOG: Đơn REQ-001 được xử lý bởi director - Kết quả: APPROVED
 *
 * ==================================================
 * 📊 BÁO CÁO TỔNG KẾT WORKFLOW
 * ==================================================
 * 🎉 REQ-001: Hoàn thành
 *
 * 📈 Thống kê:
 *    - Hoàn thành: 1
 *    - Đang chờ: 0
 *    - Bị từ chối: 0
 *    - Tổng cộng: 1
 *
 * Process finished with exit code 0
 */
