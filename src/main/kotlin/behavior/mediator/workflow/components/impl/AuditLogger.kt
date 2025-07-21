package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.mediator.WorkflowMediator

// AuditLogger.kt - Ghi log
class AuditLogger : WorkflowComponent {
    override val componentId = "audit_logger"
    override val componentType = "Audit Logger"

    private lateinit var mediator: WorkflowMediator
    private val logs = mutableListOf<String>()

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun log(activity: String) {
        val timestamp = java.time.LocalDateTime.now()
        val logEntry = "[$timestamp] $activity"
        logs.add(logEntry)
        println("📋 LOG: $activity")
    }

    fun getAllLogs(): List<String> = logs.toList()
}
