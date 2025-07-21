package behavior.mediator.gui.mediator.impl

import behavior.mediator.gui.components.Component
import behavior.mediator.gui.components.impl.*
import behavior.mediator.gui.mediator.Mediator
import behavior.mediator.gui.Note
import java.awt.*
import javax.swing.*
import javax.swing.border.LineBorder

/**
 * Concrete mediator. All chaotic communications between concrete components
 * have been extracted to the mediator. Now components only talk with the
 * mediator, which knows who has to handle a request.
 */
class Editor : Mediator {
    private lateinit var title: Title
    private lateinit var textBox: TextBox
    private lateinit var add: AddButton
    private lateinit var del: DeleteButton
    private lateinit var save: SaveButton
    private lateinit var list: NotesList
    private lateinit var filter: Filter

    private val titleLabel = JLabel("Title:")
    private val textLabel = JLabel("Text:")
    private val label = JLabel("Add or select existing note to proceed...")

    /**
     * Here the registration of components by the mediator.
     */
    override fun registerComponent(component: Component) {
        component.setMediator(this)

        when (component.componentName) {
            "AddButton" -> add = component as AddButton
            "DelButton" -> del = component as DeleteButton
            "Filter" -> filter = component as Filter
            "List" -> {
                list = component as NotesList
                list.addListSelectionListener {
                    val note = list.selectedValue
                    if (note != null) {
                        getInfoFromList(note)
                    } else {
                        clear()
                    }
                }
            }
            "SaveButton" -> save = component as SaveButton
            "TextBox" -> textBox = component as TextBox
            "Title" -> title = component as Title
        }
    }

    /**
     * Various methods to handle requests from particular components.
     */
    override fun addNewNote(note: Note) {
        title.text = ""
        textBox.text = ""
        list.addElement(note)
    }

    override fun deleteNote() {
        list.deleteElement()
    }

    override fun getInfoFromList(note: Note) {
        title.text = note.name.replace('*', ' ')
        textBox.text = note.text
    }

    override fun saveChanges() {
        try {
            val note = list.selectedValue ?: return
            note.name = title.text
            note.text = textBox.text
            list.repaint()
        } catch (ignored: Exception) {
            // Handle null pointer or other exceptions
        }
    }

    override fun markNote() {
        try {
            val note = list.currentElement ?: return
            val name = note.name
            if (!name.endsWith("*")) {
                note.name = "${note.name}*"
            }
            list.repaint()
        } catch (ignored: Exception) {
            // Handle null pointer or other exceptions
        }
    }

    override fun clear() {
        title.text = ""
        textBox.text = ""
    }

    override fun sendToFilter(listModel: ListModel<Note>) {
        filter.setList(listModel)
    }

    override fun setElementsList(listModel: ListModel<Note>) {
        list.model = listModel
        list.repaint()
    }

    override fun hideElements(flag: Boolean) {
        val visible = !flag
        titleLabel.isVisible = visible
        textLabel.isVisible = visible
        title.isVisible = visible
        textBox.isVisible = visible
        save.isVisible = visible
        label.isVisible = flag
    }

    override fun createGUI() {
        val notes = JFrame("Notes").apply {
            size = Dimension(960, 600)
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            layout = null
            isResizable = false
            setLocationRelativeTo(null)
        }

        // Left panel setup
        val left = JPanel().apply {
            border = LineBorder(Color.BLACK)
            size = Dimension(320, 600)
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
        }

        // Filter panel
        val filterPanel = JPanel().apply {
            add(JLabel("Filter:"))
            filter.columns = 20
            add(filter)
            preferredSize = Dimension(280, 40)
        }

        // List panel
        val listPanel = JPanel().apply {
            size = Dimension(320, 470)
            list.fixedCellWidth = 260
            val scrollPane = JScrollPane(list).apply {
                preferredSize = Dimension(275, 410)
            }
            add(scrollPane)
        }

        // Button panel
        val buttonPanel = JPanel().apply {
            layout = FlowLayout()
            add.preferredSize = Dimension(85, 25)
            add(add)
            del.preferredSize = Dimension(85, 25)
            add(del)
        }

        left.apply {
            add(filterPanel)
            add(listPanel)
            add(buttonPanel)
        }

        // Right panel setup
        val right = JPanel().apply {
            layout = null
            size = Dimension(640, 600)
            location = Point(320, 0)
            border = LineBorder(Color.BLACK)
        }

        // Configure right panel components
        titleLabel.bounds = Rectangle(20, 4, 50, 20)
        title.bounds = Rectangle(60, 5, 555, 20)
        textLabel.bounds = Rectangle(20, 4, 50, 130)
        textBox.apply {
            border = LineBorder(Color.DARK_GRAY)
            bounds = Rectangle(20, 80, 595, 410)
        }
        save.bounds = Rectangle(270, 535, 80, 25)
        label.apply {
            font = Font("Verdana", Font.PLAIN, 22)
            bounds = Rectangle(100, 240, 500, 100)
        }

        right.apply {
            add(label)
            add(titleLabel)
            add(title)
            add(textLabel)
            add(textBox)
            add(save)
        }

        notes.contentPane.apply {
            add(left)
            add(right)
        }

        notes.isVisible = true
    }
}
