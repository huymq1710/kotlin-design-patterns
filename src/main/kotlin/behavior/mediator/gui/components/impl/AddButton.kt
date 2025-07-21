package behavior.mediator.gui.components.impl

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.mediator.Mediator
import behavior.mediator.gui.Note
import java.awt.event.ActionEvent
import javax.swing.JButton

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
class AddButton : JButton("Add"), Component {
    private lateinit var mediator: Mediator

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun fireActionPerformed(actionEvent: ActionEvent) {
        mediator.addNewNote(Note())
    }

    override val componentName: String = "AddButton"
}
