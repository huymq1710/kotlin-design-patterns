package creation.builder.director

import creation.builder.builders.Builder
import creation.builder.cars.CarType
import creation.builder.components.Engine
import creation.builder.components.GPSNavigator
import creation.builder.components.Transmission
import creation.builder.components.TripComputer

class Director {
    fun constructSportsCar(builder: Builder) {
        builder.apply {
            carType = CarType.SPORTS_CAR
            seats = 2
            engine = Engine(3.0, 0.0)
            transmission = Transmission.SEMI_AUTOMATIC
            tripComputer = TripComputer()
            gpsNavigator = GPSNavigator()
        }
    }

    fun constructCityCar(builder: Builder) {
        builder.apply {
            carType = CarType.CITY_CAR
            seats = 2
            engine = Engine(1.2, 0.0)
            transmission = Transmission.AUTOMATIC
            tripComputer = TripComputer()
            gpsNavigator = GPSNavigator()
        }
    }

    fun constructSUV(builder: Builder) {
        builder.apply {
            carType = CarType.SUV
            seats = 4
            engine = Engine(2.5, 0.0)
            transmission = Transmission.MANUAL
            tripComputer = TripComputer()
            gpsNavigator = GPSNavigator()
        }
    }
}
