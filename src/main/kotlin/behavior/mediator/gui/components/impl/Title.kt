package behavior.mediator.gui.components.impl

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.mediator.Mediator
import java.awt.event.KeyEvent
import javax.swing.JTextField

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
class Title : JTextField(), Component {
    private lateinit var mediator: Mediator

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun processComponentKeyEvent(keyEvent: KeyEvent) {
        mediator.markNote()
    }

    override val componentName: String = "Title"
}
