package behavior.state.states

import behavior.state.ui.Player

/**
 * They can also trigger state transitions in the context.
 */
class ReadyState(player: Player) : State(player) {

    override fun onLock(): String {
        player.changeState(LockedState(player))
        return "Locked..."
    }

    override fun onPlay(): String {
        val action = player.startPlayback()
        player.changeState(PlayingState(player))
        return action
    }

    override fun onNext(): String = "Locked..."

    override fun onPrevious(): String = "Locked..."
}
