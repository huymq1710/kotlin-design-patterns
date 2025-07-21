package behavior.observer

import behavior.observer.editor.Editor
import behavior.observer.listeners.EmailNotificationListener
import behavior.observer.listeners.LogOpenListener

fun main() {
    val editor = Editor()

    // Subscribe using class instances
    editor.events.subscribe("open", LogOpenListener("/path/to/log/file.txt"))
    editor.events.subscribe("save", EmailNotificationListener("admin@example.com"))

    // Subscribe using lambda expressions (showcasing functional interface)
    editor.events.subscribe("open") { eventType, file ->
        println("Lambda listener: $eventType event occurred on file ${file.name}")
    }

    try {
        editor.openFile("test.txt")
        editor.saveFile()
    } catch (e: IllegalStateException) {
        println("Error: ${e.message}")
    }
}
