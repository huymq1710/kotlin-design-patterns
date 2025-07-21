package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.dataclass.ApprovalDecision
import behavior.mediator.workflow.mediator.WorkflowMediator

// DirectorApprover.kt - Giám đốc
class DirectorApprover(private val directorName: String) : WorkflowComponent {
    override val componentId = "director"
    override val componentType = "Director"

    private lateinit var mediator: WorkflowMediator

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun processRequest(requestId: String, decision: ApprovalDecision, comments: String) {
        println("⭐ $directorName phê duyệt cuối cùng cho đơn $requestId")
        println("   - Quyết định: ${if(decision == ApprovalDecision.APPROVED) "Chấp thuận" else "Từ chối"}")
        println("   - Nhận xét: $comments")

        mediator.processApproval(requestId, componentId, decision, comments)
    }
}
