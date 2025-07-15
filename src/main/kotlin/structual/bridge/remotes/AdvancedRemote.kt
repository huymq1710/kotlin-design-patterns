package structual.bridge.remotes

import structual.bridge.devices.Device

class AdvancedRemote(device: Device) : BasicRemote(device) {

    fun mute() {
        println("Remote: mute")
        device.volume = 0
    }
}
