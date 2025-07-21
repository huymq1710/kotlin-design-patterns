package behavior.state.states

import behavior.state.ui.Player

/**
 * Common interface for all states.
 */
abstract class State(protected val player: Player) {
    /**
     * Context passes itself through the state constructor. This may help a
     * state to fetch some useful context data if needed.
     */
    
    abstract fun onLock(): String
    abstract fun onPlay(): String
    abstract fun onNext(): String
    abstract fun onPrevious(): String
}
