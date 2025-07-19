package creation.builder.cars

import creation.builder.components.Engine
import creation.builder.components.GPSNavigator
import creation.builder.components.Transmission
import creation.builder.components.TripComputer

/**
 * Car is a product class.
 */
class Car(
    val carType: CarType,
    val seats: Int,
    val engine: Engine,
    val transmission: Transmission,
    val tripComputer: TripComputer?,
    val gpsNavigator: GPSNavigator?
) {
    var fuel: Double = 0.0
}
