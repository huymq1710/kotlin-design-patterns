package behavior.memento.history

import behavior.memento.editor.Editor

data class Memento(
    private val backup: String,
    private val editor: Editor
) {
    fun restore() {
        editor.restore(backup)
    }
}
