package behavior.command.commands

import java.util.*

/**
 * Command history using type-safe Stack operations
 */
class CommandHistory {
    private val history = Stack<Command>()

    fun push(command: Command) {
        history.push(command)
    }

    fun pop(): Command? = if (history.isNotEmpty()) history.pop() else null

    val isEmpty: Boolean get() = history.isEmpty()
}
