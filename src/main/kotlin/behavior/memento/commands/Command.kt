package behavior.memento.commands

interface Command {
    val name: String
    fun execute()
}
