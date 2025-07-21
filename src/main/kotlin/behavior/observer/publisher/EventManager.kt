package behavior.observer.publisher

import behavior.observer.listeners.EventListener

// Basic publisher
class EventManager(vararg operations: String) {
    private val listeners = operations.associateWith { mutableListOf<EventListener>() }.toMutableMap()

    fun subscribe(eventType: String, listener: EventListener) {
        listeners[eventType]?.add(listener)
    }

    fun unsubscribe(eventType: String, listener: EventListener) {
        listeners[eventType]?.remove(listener)
    }

    fun notify(eventType: String, file: java.io.File) {
        listeners[eventType]?.forEach { listener ->
            listener.update(eventType, file)
        }
    }
}
