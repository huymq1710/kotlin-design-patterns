package behavior.state

import behavior.state.ui.Player
import behavior.state.ui.UI

/**
 * Demo class. Everything comes together here.
 */
fun main() {
    val player = Player()
    val ui = UI(player)
    ui.init()
}

/**
 * Sample output:
 *
 * 🎵 Media Player Console
 * Commands: play, stop, next, prev, quit
 * Current status: Ready
 * Type 'help' for commands or 'quit' to exit
 *
 * > help
 * Available commands:
 * - play/p: Start or pause playback
 * - stop/s: Stop playback and lock player
 * - next/n: Next track
 * - prev/b: Previous track
 * - quit/q: Exit application
 *
 * > p
 * 🎵 Playing Track 1
 *
 * > s
 * ⏹️ Stop playing
 *
 * > n
 * ⏭️ Locked...
 *
 * > b
 * ⏮️ Locked...
 *
 * > s
 * ⏹️ Locked...
 *
 * > p
 * 🎵 Ready
 *
 * > n
 * ⏭️ Locked...
 *
 * > p
 * 🎵 Playing Track 1
 *
 * > n
 * ⏭️ Playing Track 2
 *
 * > q
 * 👋 Goodbye!
 */
