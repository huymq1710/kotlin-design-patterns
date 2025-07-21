package behavior.mediator.workflow.dataclass

data class LeaveRequest(
    val id: String,
    val employeeId: String,
    val employeeName: String,
    val leaveType: LeaveType,
    val startDate: String,
    val endDate: String,
    val reason: String,
    var currentStep: WorkflowStep = WorkflowStep.SUBMITTED,
    var status: WorkflowStatus = WorkflowStatus.PENDING,
    val approvals: MutableList<ApprovalRecord> = mutableListOf(),
    val comments: MutableList<String> = mutableListOf()
)

data class ApprovalRecord(
    val approver: String,
    val decision: ApprovalDecision,
    val comments: String,
    val timestamp: Long = System.currentTimeMillis()
)

enum class LeaveType(val displayName: String) {
    ANNUAL("Nghỉ phép năm"),
    SICK("Nghỉ ốm"),
    PERSONAL("Nghỉ việc riêng"),
    MATERNITY("Nghỉ thai sản")
}

enum class WorkflowStep(val displayName: String) {
    SUBMITTED("Đã gửi"),
    SUPERVISOR_REVIEW("Quản lý trực tiếp xem xét"),
    HR_REVIEW("Phòng nhân sự xem xét"),
    DIRECTOR_REVIEW("Giám đốc xem xét"),
    COMPLETED("Hoàn thành"),
    REJECTED("Bị từ chối")
}

enum class WorkflowStatus(val displayName: String) {
    PENDING("Chờ xử lý"),
    APPROVED("Đã phê duyệt"),
    REJECTED("Bị từ chối"),
    COMPLETED("Hoàn thành")
}

enum class ApprovalDecision {
    APPROVED, REJECTED, PENDING
}

enum class NotificationType {
    EMAIL, SMS, SYSTEM
}
