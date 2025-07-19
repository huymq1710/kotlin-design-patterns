package struction.bridge.remotes

import struction.bridge.devices.Device

open class BasicRemote(protected val device: Device) : Remote {

    override fun power() {
        println("Remote: power toggle")
        if (device.isEnabled) {
            device.disable()
        } else {
            device.enable()
        }
    }

    override fun volumeDown() {
        println("Remote: volume down")
        device.volume -= 10
    }

    override fun volumeUp() {
        println("Remote: volume up")
        device.volume += 10
    }

    override fun channelDown() {
        println("Remote: channel down")
        device.channel -= 1
    }

    override fun channelUp() {
        println("Remote: channel up")
        device.channel += 1
    }
}
