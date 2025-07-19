package creation.builder.builders

import creation.builder.cars.Car
import creation.builder.cars.CarType
import creation.builder.components.Engine
import creation.builder.components.GPSNavigator
import creation.builder.components.Transmission
import creation.builder.components.TripComputer

/**
 * Concrete builders implement steps defined in the common interface.
 */
class CarBuilder : Builder {
    override lateinit var carType: CarType
    override var seats: Int = 4
    override lateinit var engine: Engine
    override lateinit var transmission: Transmission
    override var tripComputer: TripComputer? = null
    override var gpsNavigator: GPSNavigator? = null

    fun build(): Car {
        return Car(
            carType,
            seats,
            engine,
            transmission,
            tripComputer,
            gpsNavigator
        )
    }
}
