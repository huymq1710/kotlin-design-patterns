package behavior.state.ui

/**
 * Player's console-based UI.
 * This replaces the Java Swing UI with a console interface for Kotlin demonstration.
 */
class UI(private val player: Player) {

    fun init() {
        println("🎵 Media Player Console")
        println("Commands: play, stop, next, prev, quit")
        println("Current status: Ready")
        println("Type 'help' for commands or 'quit' to exit")
        
        // Simple console interaction loop
        var running = true
        while (running) {
            print("\n> ")
            val input = readlnOrNull()?.lowercase()?.trim() ?: continue
            
            val result = when (input) {
                "play", "p" -> {
                    val response = player.getState().onPlay()
                    "🎵 $response"
                }
                "stop", "s" -> {
                    val response = player.getState().onLock()
                    "⏹️ $response"
                }
                "next", "n" -> {
                    val response = player.getState().onNext()
                    "⏭️ $response"
                }
                "prev", "previous", "b" -> {
                    val response = player.getState().onPrevious()
                    "⏮️ $response"
                }
                "help", "h" -> {
                    """
                    Available commands:
                    - play/p: Start or pause playback
                    - stop/s: Stop playback and lock player
                    - next/n: Next track
                    - prev/b: Previous track
                    - quit/q: Exit application
                    """.trimIndent()
                }
                "quit", "q", "exit" -> {
                    running = false
                    "👋 Goodbye!"
                }
                else -> "❓ Unknown command. Type 'help' for available commands."
            }
            
            println(result)
        }
    }
}
