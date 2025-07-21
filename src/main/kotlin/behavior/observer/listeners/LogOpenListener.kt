package behavior.observer.listeners

// Writes a message to a log upon receiving notification
class LogOpenListener(fileName: String) : EventListener {
    private val log = java.io.File(fileName)

    override fun update(eventType: String, file: java.io.File) {
        println("Save to log $log: " +
                "Someone has performed $eventType operation with the following file: ${file.name}"
        )
    }
}
