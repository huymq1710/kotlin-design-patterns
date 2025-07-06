package creational.builder.builders

import creational.builder.cars.CarType
import creational.builder.cars.CarManual
import creational.builder.components.Engine
import creational.builder.components.GPSNavigator
import creational.builder.components.Transmission
import creational.builder.components.TripComputer

/**
 * Unlike other creation patterns, Builder can construct unrelated products,
 * which don't have the common interface.
 *
 * In this case we build a user manual for a car, using the same steps as we
 * built a car.
 *
 * This allows to produce manuals for specific car models,
 * configured with different features.
 */
class CarManualBuilder : Builder {
    override lateinit var carType: CarType
    override var seats: Int = 4
    override lateinit var engine: Engine
    override lateinit var transmission: Transmission
    override var tripComputer: TripComputer? = null
    override var gpsNavigator: GPSNavigator? = null

    fun build(): CarManual {
        return CarManual(
            carType,
            seats,
            engine,
            transmission,
            tripComputer,
            gpsNavigator
        )
    }
}
