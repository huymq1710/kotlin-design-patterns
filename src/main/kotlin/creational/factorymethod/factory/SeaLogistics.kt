package creational.factorymethod.factory

import creational.factorymethod.transport.impl.Ship
import creational.factorymethod.transport.Transport

class SeaLogistics : Logistics() {
    override fun createTransport(): Transport {
        return Ship()
    }
}
