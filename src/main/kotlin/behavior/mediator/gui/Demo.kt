package behavior.mediator.gui

import behavior.mediator.gui.components.impl.*
import behavior.mediator.gui.mediator.impl.Editor
import javax.swing.DefaultListModel

/**
 * Demo class. Everything comes together here.
 */
fun main() {
    val mediator = Editor()

    mediator.apply {
        registerComponent(Title())
        registerComponent(TextBox())
        registerComponent(AddButton())
        registerComponent(DeleteButton())
        registerComponent(SaveButton())
        registerComponent(NotesList(DefaultListModel()))
        registerComponent(Filter())

        createGUI()
    }
}
