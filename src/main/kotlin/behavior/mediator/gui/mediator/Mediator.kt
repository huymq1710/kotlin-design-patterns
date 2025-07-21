package behavior.mediator.gui.mediator

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.dataclass.Note
import javax.swing.ListModel

/**
 * Common mediator interface.
 */
interface Mediator {
    fun addNewNote(note: Note)
    fun deleteNote()
    fun getInfoFromList(note: Note)
    fun saveChanges()
    fun markNote()
    fun clear()
    fun sendToFilter(listModel: ListModel<Note>)
    fun setElementsList(listModel: ListModel<Note>)
    fun registerComponent(component: Component)
    fun hideElements(flag: Boolean)
    fun createGUI()
}
