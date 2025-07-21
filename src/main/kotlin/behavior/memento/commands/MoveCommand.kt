package behavior.memento.commands

import behavior.memento.editor.Editor

class MoveCommand(private val editor: Editor) : Command {
    private var startX = 0
    private var startY = 0
    private var endX = 0
    private var endY = 0

    override val name: String
        get() = "Move by X:${endX - startX} Y:${endY - startY}"

    fun start(x: Int, y: Int) {
        startX = x
        startY = y
        editor.shapes.selected.forEach { it.drag() }
    }

    fun move(x: Int, y: Int) {
        editor.shapes.selected.forEach { shape ->
            shape.moveTo(x - startX, y - startY)
        }
    }

    fun stop(x: Int, y: Int) {
        endX = x
        endY = y
        editor.shapes.selected.forEach { it.drop() }
    }

    override fun execute() {
        editor.shapes.selected.forEach { shape ->
            shape.moveBy(endX - startX, endY - startY)
        }
    }
}
