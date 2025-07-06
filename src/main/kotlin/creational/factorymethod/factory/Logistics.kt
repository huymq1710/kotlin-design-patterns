package creational.factorymethod.factory

import creational.factorymethod.transport.Transport

abstract class Logistics {
    abstract fun createTransport(): Transport
}
