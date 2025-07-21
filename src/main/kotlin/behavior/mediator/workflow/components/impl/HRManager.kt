package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.ApprovalDecision
import behavior.mediator.workflow.mediator.WorkflowMediator

// HRManager.kt - Phòng nhân sự
class HRManager(private val hrName: String) : WorkflowComponent {
    override val componentId = "hr"
    override val componentType = "HR Manager"

    private lateinit var mediator: WorkflowMediator

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun processRequest(requestId: String, decision: ApprovalDecision, comments: String) {
        println("🏢 $hrName xử lý đơn $requestId")
        println("   - Kiểm tra chính sách công ty")
        println("   - Quyết định: ${if(decision == ApprovalDecision.APPROVED) "Chấp thuận" else "Từ chối"}")
        println("   - Nhận xét: $comments")

        mediator.processApproval(requestId, componentId, decision, comments)
    }
}
