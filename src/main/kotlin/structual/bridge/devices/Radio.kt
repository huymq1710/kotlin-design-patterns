package structual.bridge.devices

class Radio : Device {
    private var on = false
    private var _volume = 30
    private var _channel = 1

    override val isEnabled: Boolean
        get() = on

    override var volume: Int
        get() = _volume
        set(value) {
            _volume = value.coerceIn(0, 100)
        }

    override var channel: Int
        get() = _channel
        set(value) {
            _channel = value
        }

    override fun enable() {
        on = true
    }

    override fun disable() {
        on = false
    }

    override fun printStatus() {
        println("""
            ------------------------------------
            | I'm radio.
            | I'm ${if (on) "enabled" else "disabled"}
            | Current volume is ${volume}%
            | Current channel is $channel
            ------------------------------------
        """.trimIndent())
        println()
    }
}
