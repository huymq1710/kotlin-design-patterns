package behavior.command

import behavior.command.editor.Editor

/**
 * Client code demonstrating the Command pattern
 */
object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val editor = Editor()
        editor.init()
    }
}
