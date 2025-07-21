package behavior.mediator.gui.dataclass


/**
 * Note class.
 */
data class Note(
    var name: String = "New note",
    var text: String = ""
) {
    override fun toString(): String = name
}
