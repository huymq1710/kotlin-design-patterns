package behavior.mediator.workflow.components

import behavior.mediator.workflow.mediator.WorkflowMediator

// WorkflowComponent.kt - Interface cho các thành phần workflow
interface WorkflowComponent {
    val componentId: String
    val componentType: String
    fun setMediator(mediator: WorkflowMediator)
}
