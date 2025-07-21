package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.dataclass.LeaveRequest
import behavior.mediator.workflow.mediator.WorkflowMediator

// RequestSubmitter.kt - Người gửi đơn
class RequestSubmitter(private val employeeName: String) : WorkflowComponent {
    override val componentId = "submitter_${employeeName.replace(" ", "_")}"
    override val componentType = "Request Submitter"

    private lateinit var mediator: WorkflowMediator

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun submitRequest(request: LeaveRequest) {
        println("📝 $employeeName gửi đơn xin nghỉ phép: ${request.id}")
        println("   - Loại: ${request.leaveType.displayName}")
        println("   - Từ: ${request.startDate} đến ${request.endDate}")
        println("   - Lý do: ${request.reason}")

        mediator.submitRequest(request)
    }
}
