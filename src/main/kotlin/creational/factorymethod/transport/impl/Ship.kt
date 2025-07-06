package creational.factorymethod.transport.impl

import creational.factorymethod.transport.Transport

class Ship : Transport {
    override fun deliver() {
        println("Deliver by sea in a container")
    }
}
