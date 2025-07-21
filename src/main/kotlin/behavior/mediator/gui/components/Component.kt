package behavior.mediator.gui.components

import behavior.mediator.gui.mediator.Mediator

/**
 * Common component interface.
 */
interface Component {
    fun setMediator(mediator: Mediator)
    val componentName: String
}
