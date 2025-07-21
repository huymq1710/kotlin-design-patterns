package behavior.state.ui

import behavior.state.states.ReadyState
import behavior.state.states.State

class Player {
    private var state: State
    private var playing = false
    private val playlist = mutableListOf<String>()
    private var currentTrack = 0

    init {
        this.state = ReadyState(this)
        setPlaying(true)
        // Initialize playlist with 12 tracks
        repeat(12) { i ->
            playlist.add("Track ${i + 1}")
        }
    }

    fun changeState(state: State) {
        this.state = state
    }

    fun getState(): State = state

    fun setPlaying(playing: Boolean) {
        this.playing = playing
    }

    fun isPlaying(): Boolean = playing

    fun startPlayback(): String = "Playing ${playlist[currentTrack]}"

    fun nextTrack(): String {
        currentTrack = (currentTrack + 1) % playlist.size
        return "Playing ${playlist[currentTrack]}"
    }

    fun previousTrack(): String {
        currentTrack = if (currentTrack - 1 < 0) playlist.size - 1 else currentTrack - 1
        return "Playing ${playlist[currentTrack]}"
    }

    fun setCurrentTrackAfterStop() {
        this.currentTrack = 0
    }
}
