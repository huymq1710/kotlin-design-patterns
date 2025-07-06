package creational.factorymethod

import creational.factorymethod.factory.Logistics
import creational.factorymethod.factory.SeaLogistics
import creational.factorymethod.factory.RoadLogistics

fun main() {
    val option = "See"

    val logistics: Logistics = when (option) {
        "See" -> SeaLogistics()
        "Road" -> RoadLogistics()
        else -> throw Exception("Not supported yet")
    }

    val transport = logistics.createTransport()
    transport.deliver() // "Deliver by sea in a container"
}
