package creation.factorymethod.transport.impl

import creation.factorymethod.transport.Transport

class Ship : Transport {
    override fun deliver() {
        println("Deliver by sea in a container")
    }
}
