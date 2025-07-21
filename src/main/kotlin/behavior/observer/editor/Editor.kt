package behavior.observer.editor

import behavior.observer.publisher.EventManager

// Concrete publisher, tracked by other objects
class Editor {
    val events = EventManager("open", "save")
    private var file: java.io.File? = null

    fun openFile(filePath: String) {
        file = java.io.File(filePath)
        events.notify("open", file!!)
    }

    @Throws(IllegalStateException::class)
    fun saveFile() {
        val currentFile = file ?: error("Please open a file first.")
        events.notify("save", currentFile)
    }
}
