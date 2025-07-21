package behavior.memento.editor

import behavior.memento.commands.Command
import behavior.memento.history.History
import behavior.memento.history.Memento
import behavior.memento.shapes.CompoundShape
import behavior.memento.shapes.Shape
import java.io.*
import java.util.*
import javax.swing.JComponent

class Editor : JComponent() {
    private val canvas: Canvas
    val shapes = CompoundShape()
    private val history = History()

    init {
        canvas = Canvas(this)
    }

    fun loadShapes(vararg shapes: Shape) {
        this.shapes.clear()
        this.shapes.add(*shapes)
        canvas.refresh()
    }

    fun execute(command: Command) {
        history.push(command, Memento(backup(), this))
        command.execute()
    }

    fun undo() {
        if (history.undo()) {
            canvas.repaint()
        }
    }

    fun redo() {
        if (history.redo()) {
            canvas.repaint()
        }
    }

    fun backup(): String {
        return try {
            val baos = ByteArrayOutputStream()
            val oos = ObjectOutputStream(baos)
            oos.writeObject(shapes)
            oos.close()
            Base64.getEncoder().encodeToString(baos.toByteArray())
        } catch (e: IOException) {
            ""
        }
    }

    fun restore(state: String) {
        try {
            val data = Base64.getDecoder().decode(state)
            val ois = ObjectInputStream(ByteArrayInputStream(data))
            val restoredShapes = ois.readObject() as CompoundShape
            ois.close()

            shapes.clear()
            shapes.add(*restoredShapes.selected.toTypedArray())
        } catch (e: ClassNotFoundException) {
            println("ClassNotFoundException occurred.")
        } catch (e: IOException) {
            println("IOException occurred.")
        }
    }
}
