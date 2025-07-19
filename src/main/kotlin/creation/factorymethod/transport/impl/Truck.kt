package creation.factorymethod.transport.impl

import creation.factorymethod.transport.Transport

class Truck : Transport {
    override fun deliver() {
        println("Deliver by land in a box")
    }
}
