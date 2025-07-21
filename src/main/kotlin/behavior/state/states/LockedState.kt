package behavior.state.states

import behavior.state.ui.Player

/**
 * Concrete states provide the special implementation for all interface methods.
 */
class LockedState(player: Player) : State(player) {
    
    init {
        player.setPlaying(false)
    }

    override fun onLock(): String {
        return if (player.isPlaying()) {
            player.changeState(ReadyState(player))
            "Stop playing"
        } else {
            "Locked..."
        }
    }

    override fun onPlay(): String {
        player.changeState(ReadyState(player))
        return "Ready"
    }

    override fun onNext(): String = "Locked..."

    override fun onPrevious(): String = "Locked..."
}
