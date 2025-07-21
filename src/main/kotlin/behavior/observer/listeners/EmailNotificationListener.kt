package behavior.observer.listeners

// Sends emails upon receiving notification
class EmailNotificationListener(private val email: String) : EventListener {
    override fun update(eventType: String, file: java.io.File) {
        println("Email to $email: Someone has performed $eventType operation with the following file: ${file.name}")
    }
}
