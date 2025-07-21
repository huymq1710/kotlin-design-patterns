package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.WorkflowStatus
import behavior.mediator.workflow.mediator.WorkflowMediator

// WorkflowDashboard.kt - Bảng điều khiển
class WorkflowDashboard : WorkflowComponent {
    override val componentId = "dashboard"
    override val componentType = "Workflow Dashboard"

    private lateinit var mediator: WorkflowMediator
    private val requestStatuses = mutableMapOf<String, WorkflowStatus>()

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun updateStatus(requestId: String, status: WorkflowStatus) {
        requestStatuses[requestId] = status
        println("📊 Dashboard cập nhật: $requestId -> ${status.displayName}")
    }

    fun showSummary() {
        println("\n" + "=".repeat(50))
        println("📊 BÁO CÁO TỔNG KẾT WORKFLOW")
        println("=".repeat(50))

        requestStatuses.forEach { (requestId, status) ->
            val statusIcon = when(status) {
                WorkflowStatus.PENDING -> "⏳"
                WorkflowStatus.APPROVED -> "✅"
                WorkflowStatus.REJECTED -> "❌"
                WorkflowStatus.COMPLETED -> "🎉"
            }
            println("$statusIcon $requestId: ${status.displayName}")
        }

        val completed = requestStatuses.values.count { it == WorkflowStatus.COMPLETED }
        val pending = requestStatuses.values.count { it == WorkflowStatus.PENDING }
        val rejected = requestStatuses.values.count { it == WorkflowStatus.REJECTED }

        println("\n📈 Thống kê:")
        println("   - Hoàn thành: $completed")
        println("   - Đang chờ: $pending")
        println("   - Bị từ chối: $rejected")
        println("   - Tổng cộng: ${requestStatuses.size}")
    }
}
