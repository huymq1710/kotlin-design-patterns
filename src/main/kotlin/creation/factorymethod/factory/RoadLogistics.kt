package creation.factorymethod.factory

import creation.factorymethod.transport.impl.Truck
import creation.factorymethod.transport.Transport

class RoadLogistics : Logistics() {
    override fun createTransport(): Transport {
        return Truck()
    }
}
