package behavior.mediator.workflow.mediator.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.components.impl.AuditLogger
import behavior.mediator.workflow.components.impl.NotificationSystem
import behavior.mediator.workflow.components.impl.WorkflowDashboard
import behavior.mediator.workflow.dataclass.*
import behavior.mediator.workflow.mediator.WorkflowMediator

class WorkflowManager : WorkflowMediator {
    private val components = mutableMapOf<String, WorkflowComponent>()
    private val activeRequests = mutableMapOf<String, LeaveRequest>()
    private val workflowSteps = listOf(
        WorkflowStep.SUBMITTED,
        WorkflowStep.SUPERVISOR_REVIEW,
        WorkflowStep.HR_REVIEW,
        WorkflowStep.DIRECTOR_REVIEW,
        WorkflowStep.COMPLETED
    )

    // Method 1: Get next step in workflow
    private fun getNextStep(currentStep: WorkflowStep): WorkflowStep? {
        val currentIndex = workflowSteps.indexOf(currentStep)
        return if (currentIndex >= 0 && currentIndex < workflowSteps.size - 1) {
            workflowSteps[currentIndex + 1]
        } else null
    }

    // Method 2: Calculate workflow progress
    private fun getWorkflowProgress(currentStep: WorkflowStep): Double {
        val currentIndex = workflowSteps.indexOf(currentStep)
        return if (currentIndex >= 0) {
            (currentIndex.toDouble() / (workflowSteps.size - 1)) * 100
        } else 0.0
    }

    // Method 3: Validate step transitions
    private fun isValidStepTransition(from: WorkflowStep, to: WorkflowStep): Boolean {
        val fromIndex = workflowSteps.indexOf(from)
        val toIndex = workflowSteps.indexOf(to)

        // Allow forward progression or rejection from any step
        return toIndex == fromIndex + 1 || to == WorkflowStep.REJECTED
    }

    // Method 4: Get remaining steps
    private fun getRemainingSteps(currentStep: WorkflowStep): List<WorkflowStep> {
        val currentIndex = workflowSteps.indexOf(currentStep)
        return if (currentIndex >= 0 && currentIndex < workflowSteps.size - 1) {
            workflowSteps.subList(currentIndex + 1, workflowSteps.size)
        } else emptyList()
    }

    override fun registerComponent(component: WorkflowComponent) {
        component.setMediator(this)
        components[component.componentId] = component
        println("✅ Đăng ký thành phần: ${component.componentType} (${component.componentId})")
    }

    override fun submitRequest(request: LeaveRequest) {
        activeRequests[request.id] = request
        logActivity("Đơn ${request.id} được gửi bởi ${request.employeeName}")

        // Use workflowSteps to get the next step
        val nextStep = getNextStep(WorkflowStep.SUBMITTED)
        if (nextStep != null) {
            request.currentStep = nextStep
            updateDashboard(request.id, WorkflowStatus.PENDING)

            // Log progress
            val progress = getWorkflowProgress(nextStep)
            println("📊 Tiến độ workflow: ${String.format("%.1f", progress)}%")

            // Send notification for next step
            sendNotification(
                "supervisor",
                "Có đơn xin nghỉ phép mới cần xem xét: ${request.id}",
                NotificationType.SYSTEM
            )
        }
    }

    override fun processApproval(
        requestId: String, fromComponent: String, decision: ApprovalDecision, comments: String
    ) {
        val request = activeRequests[requestId] ?: return

        // Add approval record
        request.approvals.add(ApprovalRecord(fromComponent, decision, comments))
        request.comments.add("$fromComponent: $comments")

        if (decision == ApprovalDecision.REJECTED) {
            request.status = WorkflowStatus.REJECTED
            request.currentStep = WorkflowStep.REJECTED
            updateDashboard(requestId, WorkflowStatus.REJECTED)
            sendNotification(request.employeeId, "Đơn ${requestId} đã bị từ chối", NotificationType.EMAIL)
            logActivity("Đơn $requestId bị từ chối bởi $fromComponent")
            return
        }

        // Use workflowSteps to determine next step
        val nextStep = getNextStep(request.currentStep)

        if (nextStep != null) {
            // Validate transition
            if (isValidStepTransition(request.currentStep, nextStep)) {
                request.currentStep = nextStep

                // Show progress
                val progress = getWorkflowProgress(nextStep)
                println("📊 Tiến độ workflow cho ${requestId}: ${String.format("%.1f", progress)}%")

                // Show remaining steps
                val remainingSteps = getRemainingSteps(nextStep)
                if (remainingSteps.isNotEmpty()) {
                    println("🔄 Các bước còn lại: ${remainingSteps.joinToString(" → ") { it.displayName }}")
                }

                // Send notifications based on next step
                when (nextStep) {
                    WorkflowStep.HR_REVIEW -> {
                        sendNotification(
                            "hr",
                            "Đơn $requestId cần xem xét từ HR", NotificationType.SYSTEM
                        )
                    }
                    WorkflowStep.DIRECTOR_REVIEW -> {
                        sendNotification(
                            "director",
                            "Đơn $requestId cần phê duyệt từ Giám đốc", NotificationType.SYSTEM
                        )
                    }
                    WorkflowStep.COMPLETED -> {
                        completeWorkflow(requestId)
                    }
                    else -> {}
                }
            } else {
                println("⚠️ Chuyển đổi bước không hợp lệ từ ${request.currentStep} đến $nextStep")
                return
            }
        } else {
            // No next step, complete workflow
            completeWorkflow(requestId)
        }

        logActivity("Đơn $requestId được xử lý bởi $fromComponent - Kết quả: $decision")
    }

    override fun sendNotification(recipient: String, message: String, type: NotificationType) {
        val notificationSystem = components["notification_system"] as? NotificationSystem
        notificationSystem?.sendNotification(recipient, message, type)
    }

    override fun logActivity(activity: String) {
        val auditLogger = components["audit_logger"] as? AuditLogger
        auditLogger?.log(activity)
    }

    override fun updateDashboard(requestId: String, status: WorkflowStatus) {
        val dashboard = components["dashboard"] as? WorkflowDashboard
        dashboard?.updateStatus(requestId, status)
    }

    override fun getRequestStatus(requestId: String): WorkflowStatus? {
        return activeRequests[requestId]?.status
    }

    override fun completeWorkflow(requestId: String) {
        val request = activeRequests[requestId] ?: return
        request.status = WorkflowStatus.COMPLETED
        request.currentStep = WorkflowStep.COMPLETED

        updateDashboard(requestId, WorkflowStatus.COMPLETED)
        sendNotification(
            request.employeeId,
            "Đơn ${requestId} đã được phê duyệt hoàn tất",
            NotificationType.EMAIL
        )
        logActivity("Đơn $requestId hoàn thành workflow")

        println("🎉 Workflow hoàn thành cho đơn: $requestId (100% hoàn thành)")
    }

    // Additional utility methods using workflowSteps

    fun getWorkflowSteps(): List<WorkflowStep> = workflowSteps.toList()

    fun getWorkflowStepDescription(step: WorkflowStep): String {
        val index = workflowSteps.indexOf(step)
        return if (index >= 0) {
            "Bước ${index + 1}/${workflowSteps.size}: ${step.displayName}"
        } else {
            "Bước không xác định: ${step.displayName}"
        }
    }

    fun validateWorkflowIntegrity(): Boolean {
        // Ensure all required steps are present
        val requiredSteps = setOf(
            WorkflowStep.SUBMITTED,
            WorkflowStep.SUPERVISOR_REVIEW,
            WorkflowStep.HR_REVIEW,
            WorkflowStep.DIRECTOR_REVIEW,
            WorkflowStep.COMPLETED
        )

        return workflowSteps.containsAll(requiredSteps)
    }

    // Method to show current workflow configuration
    fun showWorkflowConfiguration() {
        println("\n🔧 CẤU HÌNH WORKFLOW:")
        workflowSteps.forEachIndexed { index, step ->
            val arrow = if (index < workflowSteps.size - 1) " → " else ""
            print("${index + 1}. ${step.displayName}$arrow")
        }
        println("\n")
    }
}
