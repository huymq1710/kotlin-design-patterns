package behavior.memento.commands

import behavior.memento.editor.Editor
import java.awt.Color

data class ColorCommand(
    private val editor: Editor,
    private val color: Color
) : Command {

    override val name: String
        get() = "Colorize: $color"

    override fun execute() {
        editor.shapes.selected.forEach { shape ->
            shape.color = color
        }
    }
}
