package creational.builder.builders

import creational.builder.cars.CarType
import creational.builder.components.Engine
import creational.builder.components.GPSNavigator
import creational.builder.components.Transmission
import creational.builder.components.TripComputer

interface Builder {
    var carType: CarType
    var seats: Int
    var engine: Engine
    var transmission: Transmission
    var tripComputer: TripComputer?
    var gpsNavigator: GPSNavigator?
}
