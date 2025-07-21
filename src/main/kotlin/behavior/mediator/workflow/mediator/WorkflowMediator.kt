package behavior.mediator.workflow.mediator

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.dataclass.ApprovalDecision
import behavior.mediator.workflow.dataclass.LeaveRequest
import behavior.mediator.workflow.dataclass.NotificationType
import behavior.mediator.workflow.dataclass.WorkflowStatus

interface WorkflowMediator {
    fun registerComponent(component: WorkflowComponent)
    fun submitRequest(request: LeaveRequest)
    fun processApproval(requestId: String, fromComponent: String, decision: ApprovalDecision, comments: String)
    fun sendNotification(recipient: String, message: String, type: NotificationType)
    fun logActivity(activity: String)
    fun updateDashboard(requestId: String, status: WorkflowStatus)
    fun getRequestStatus(requestId: String): WorkflowStatus?
    fun completeWorkflow(requestId: String)
}
