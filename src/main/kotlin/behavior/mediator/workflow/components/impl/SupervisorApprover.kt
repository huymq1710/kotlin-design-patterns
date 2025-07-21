package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.ApprovalDecision
import behavior.mediator.workflow.mediator.WorkflowMediator

// SupervisorApprover.kt - Quản lý trực tiếp
class SupervisorApprover(private val supervisorName: String) : WorkflowComponent {
    override val componentId = "supervisor"
    override val componentType = "Supervisor"

    private lateinit var mediator: WorkflowMediator

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun processRequest(requestId: String, decision: ApprovalDecision, comments: String) {
        println("👤 $supervisorName xem xét đơn $requestId")
        println("   - Quyết định: ${if(decision == ApprovalDecision.APPROVED) "Chấp thuận" else "Từ chối"}")
        println("   - Nhận xét: $comments")

        mediator.processApproval(requestId, componentId, decision, comments)
    }
}
