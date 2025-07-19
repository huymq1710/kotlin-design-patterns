package struction.bridge.remotes

import struction.bridge.devices.Device

class AdvancedRemote(device: Device) : BasicRemote(device) {

    fun mute() {
        println("Remote: mute")
        device.volume = 0
    }
}
