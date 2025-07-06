package creational.factorymethod.factory

import creational.factorymethod.transport.impl.Truck
import creational.factorymethod.transport.Transport

class RoadLogistics : Logistics() {
    override fun createTransport(): Transport {
        return Truck()
    }
}
