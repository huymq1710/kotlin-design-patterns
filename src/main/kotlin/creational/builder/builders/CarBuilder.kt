package creational.builder.builders

import creational.builder.cars.Car
import creational.builder.cars.CarType
import creational.builder.components.Engine
import creational.builder.components.GPSNavigator
import creational.builder.components.Transmission
import creational.builder.components.TripComputer

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
