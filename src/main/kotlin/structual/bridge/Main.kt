package structual.bridge

import structual.bridge.devices.Device
import structual.bridge.devices.Radio
import structual.bridge.devices.Tv
import structual.bridge.remotes.AdvancedRemote
import structual.bridge.remotes.BasicRemote

fun main() {
    testDevice(Tv())
    testDevice(Radio())
}

fun testDevice(device: Device) {
    println("Tests with basic remote.")
    val basicRemote = BasicRemote(device)
    basicRemote.power()
    device.printStatus()

    println("Tests with advanced remote.")
    val advancedRemote = AdvancedRemote(device)
    advancedRemote.power()
    advancedRemote.mute()
    device.printStatus()
}
