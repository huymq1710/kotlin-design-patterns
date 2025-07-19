package creation.prototype

import creation.prototype.shapes.Circle
import creation.prototype.shapes.Rectangle

fun main() {
    val shapes = listOf(
        Circle(10, 20, "red", 15),
        Rectangle(5, 5, "blue", 10, 20),
        Rectangle(0, 0, "green", 30, 40)
    )

    val shapesCopy = shapes.map { it.clone() }

    for (i in shapes.indices) {
        if (shapes[i] !== shapesCopy[i]) {
            println("$i: Shapes are different objects (yay!)")
            if (shapes[i] == shapesCopy[i]) {
                println("$i: And they are identical (yay!)")
            } else {
                println("$i: But they are not identical (booo!)")
            }
        } else {
            println("$i: Shape objects are the same (booo!)")
        }
    }
}
