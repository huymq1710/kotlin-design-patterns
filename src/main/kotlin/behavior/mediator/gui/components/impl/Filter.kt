package behavior.mediator.gui.components.impl

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.mediator.Mediator
import behavior.mediator.gui.Note
import java.awt.event.KeyEvent
import javax.swing.DefaultListModel
import javax.swing.JTextField
import javax.swing.ListModel

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
class Filter : JTextField(), Component {
    private lateinit var mediator: Mediator
    private var listModel: ListModel<Note>? = null

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun processComponentKeyEvent(keyEvent: KeyEvent) {
        val start = text
        searchElements(start)
    }

    fun setList(listModel: ListModel<Note>) {
        this.listModel = listModel
    }

    private fun searchElements(searchTerm: String) {
        val currentListModel = listModel ?: return

        if (searchTerm.isEmpty()) {
            mediator.setElementsList(currentListModel)
            return
        }

        val notes = mutableListOf<Note>()
        for (i in 0 until currentListModel.size) {
            notes.add(currentListModel.getElementAt(i) as Note)
        }

        val filteredModel = DefaultListModel<Note>().apply {
            notes.filter { it.name.contains(searchTerm) }
                .forEach { addElement(it) }
        }

        mediator.setElementsList(filteredModel)
    }

    override val componentName: String = "Filter"
}
