package creation.builder.cars

import creation.builder.components.Engine
import creation.builder.components.GPSNavigator
import creation.builder.components.Transmission
import creation.builder.components.TripComputer

class CarManual(
    val carType: CarType,
    val seats: Int,
    val engine: Engine,
    val transmission: Transmission,
    val tripComputer: TripComputer?,
    val gpsNavigator: GPSNavigator?
) {
    fun print(): String = buildString {
        appendLine("- Type of car: $carType")
        appendLine("- Count of seats: $seats")
        appendLine("- Engine: volume - ${engine.volume}; mileage - ${engine.mileage}; started - ${engine.isStarted}")
        appendLine("- Transmission: $transmission")
        appendLine("- Trip Computer: " + (tripComputer?.let {
            it.fuelLevelInfo + ". " + it.statusInfo
        } ?: "N/A"))

        appendLine("- GPS Navigator: " + (gpsNavigator?.let {
            "Functional. Current route: ${it.route}"
        } ?: "N/A"))
    }
}
