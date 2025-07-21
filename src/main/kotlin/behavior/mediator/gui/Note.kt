package behavior.mediator.gui


/**
 * Note class.
 */
data class Note(
    var name: String = "New note",
    var text: String = ""
) {
    override fun toString(): String = name
}
