package creational.builder

import creational.builder.builders.CarBuilder
import creational.builder.builders.CarManualBuilder
import creational.builder.components.Engine
import creational.builder.director.Director

fun main() {
    val director = Director()

    // Director gets the concrete builder object from the client (application code)
    // That's because application knows better which
    // builder to use to get a specific product.
    val carBuilder = CarBuilder().also { director.constructSUV(it) }

    // The final product is often retrieved from a builder object, since
    // Director is not aware and not dependent on concrete builders and products.
    val car = carBuilder.build()
    println("Car built:\n- Type of car: ${car.carType}")

    // Director know detail building recipes.
    val manualBuilder = CarManualBuilder().also { director.constructSUV(it) }
    val carManual = manualBuilder.build()
    println("\nCar manual built:\n${carManual.print()}")

    println("\n--- Testing Engine ---")
    testEngine()
}


fun testEngine() {
    val engine = Engine(volume = 2.0, mileage = 0.0)

    println("Initial engine status:")
    println("Volume: ${engine.volume}")
    println("Mileage: ${engine.mileage}")
    println("Started: ${engine.isStarted}")

    println("\nTrying to go 50km without starting engine:")
    engine.go(50.0)  // Should print warning

    println("\nStarting engine...")
    engine.on()
    println("Started: ${engine.isStarted}")

    println("Driving 120km...")
    engine.go(120.0)
    println("Mileage after drive: ${engine.mileage} km")

    println("\nStopping engine...")
    engine.off()
    println("Started: ${engine.isStarted}")
}
