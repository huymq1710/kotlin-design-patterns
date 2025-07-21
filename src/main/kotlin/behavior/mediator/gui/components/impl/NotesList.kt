package behavior.mediator.gui.components.impl

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.mediator.Mediator
import behavior.mediator.gui.dataclass.Note
import javax.swing.DefaultListModel
import javax.swing.JList
import kotlin.concurrent.thread

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
class NotesList(private val listModel: DefaultListModel<Note>) : JList<Note>(listModel), Component {
    private lateinit var mediator: Mediator

    init {
        model = listModel
        layoutOrientation = VERTICAL

        // Start background thread for hiding elements
        thread(isDaemon = true) {
            while (true) {
                try {
                    Thread.sleep(300)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }

                mediator.hideElements(isSelectionEmpty)
            }
        }
    }

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    fun addElement(note: Note) {
        listModel.addElement(note)
        val index = listModel.size - 1
        selectedIndex = index
        ensureIndexIsVisible(index)
        mediator.sendToFilter(listModel)
    }

    fun deleteElement() {
        val index = selectedIndex
        try {
            listModel.remove(index)
            mediator.sendToFilter(listModel)
        } catch (ignored: ArrayIndexOutOfBoundsException) {
            // Ignore if no element selected
        }
    }

    val currentElement: Note?
        get() = selectedValue

    override val componentName: String = "List"
}
