package behavior.mediator.workflow.components.impl

import behavior.mediator.workflow.components.WorkflowComponent
import behavior.mediator.workflow.dataclass.NotificationType
import behavior.mediator.workflow.mediator.WorkflowMediator

// NotificationSystem.kt - Hệ thống thông báo
class NotificationSystem : WorkflowComponent {
    override val componentId = "notification_system"
    override val componentType = "Notification System"

    private lateinit var mediator: WorkflowMediator

    override fun setMediator(mediator: WorkflowMediator) {
        this.mediator = mediator
    }

    fun sendNotification(recipient: String, message: String, type: NotificationType) {
        val typeIcon = when(type) {
            NotificationType.EMAIL -> "📧"
            NotificationType.SMS -> "📱"
            NotificationType.SYSTEM -> "🔔"
        }

        println("$typeIcon Thông báo gửi đến $recipient: $message")
    }
}
