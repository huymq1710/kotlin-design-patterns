package creational.builder.components

import creational.builder.cars.Car

class Engine(val volume: Double, var mileage: Double) {
    var isStarted: Boolean = false

    fun on() { isStarted = true }
    fun off() { isStarted = false }
    fun go(distance: Double) {
        if (isStarted) mileage += distance
        else println("Cannot go(), you must start engine first!")
    }
}

class GPSNavigator(
    val route: String = "221b, Baker Street, London to Scotland Yard, 8-10 Broadway, London"
)

class TripComputer {
    var car: Car? = null

    val fuelLevelInfo: String
        get() = "Fuel level: ${car?.fuel ?: "Unknown"}"

    val statusInfo: String
        get() = if (car?.engine?.isStarted == true) "Car is started" else "Car isn't started"
}

enum class Transmission {
    SINGLE_SPEED, MANUAL, AUTOMATIC, SEMI_AUTOMATIC
}
