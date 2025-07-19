package creation.builder.builders

import creation.builder.cars.CarType
import creation.builder.components.Engine
import creation.builder.components.GPSNavigator
import creation.builder.components.Transmission
import creation.builder.components.TripComputer

interface Builder {
    var carType: CarType
    var seats: Int
    var engine: Engine
    var transmission: Transmission
    var tripComputer: TripComputer?
    var gpsNavigator: GPSNavigator?
}
