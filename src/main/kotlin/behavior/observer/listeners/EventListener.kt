package behavior.observer.listeners

// Common observer interface
fun interface EventListener {
    fun update(eventType: String, file: java.io.File)
}
