package creational.factorymethod.transport.impl

import creational.factorymethod.transport.Transport

class Truck : Transport {
    override fun deliver() {
        println("Deliver by land in a box")
    }
}
