package creation.factorymethod.factory

import creation.factorymethod.transport.Transport

abstract class Logistics {
    abstract fun createTransport(): Transport
}
