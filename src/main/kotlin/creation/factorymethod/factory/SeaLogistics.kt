package creation.factorymethod.factory

import creation.factorymethod.transport.impl.Ship
import creation.factorymethod.transport.Transport

class SeaLogistics : Logistics() {
    override fun createTransport(): Transport {
        return Ship()
    }
}
