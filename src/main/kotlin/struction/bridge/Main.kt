package struction.bridge

import struction.bridge.devices.Device
import struction.bridge.devices.Radio
import struction.bridge.devices.Tv
import struction.bridge.remotes.AdvancedRemote
import struction.bridge.remotes.BasicRemote

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
