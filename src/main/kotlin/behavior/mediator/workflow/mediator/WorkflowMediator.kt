package behavior.mediator.workflow.mediator

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.ApprovalDecision
import behavior.mediator.workflow.LeaveRequest
import behavior.mediator.workflow.NotificationType
import behavior.mediator.workflow.WorkflowStatus

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
