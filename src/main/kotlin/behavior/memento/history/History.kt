package behavior.memento.history

import behavior.memento.commands.Command

class History {
    private val history = mutableListOf<CommandMementoPair>()
    private var virtualSize = 0

    private data class CommandMementoPair(
        val command: Command,
        val memento: Memento
    )

    fun push(command: Command, memento: Memento) {
        if (virtualSize != history.size && virtualSize > 0) {
            repeat(history.size - virtualSize + 1) {
                if (history.isNotEmpty()) {
                    history.removeAt(history.lastIndex)
                }
            }
        }
        history.add(CommandMementoPair(command, memento))
        virtualSize = history.size
    }

    fun undo(): Boolean {
        val pair = getUndoPair() ?: return false
        println("Undoing: ${pair.command.name}")
        pair.memento.restore()
        return true
    }

    fun redo(): Boolean {
        val pair = getRedoPair() ?: return false
        println("Redoing: ${pair.command.name}")
        pair.memento.restore()
        pair.command.execute()
        return true
    }

    private fun getUndoPair(): CommandMementoPair? {
        if (virtualSize == 0) return null
        virtualSize = maxOf(0, virtualSize - 1)
        return history[virtualSize]
    }

    private fun getRedoPair(): CommandMementoPair? {
        if (virtualSize == history.size) return null
        virtualSize = minOf(history.size, virtualSize + 1)
        return history[virtualSize - 1]
    }
}
