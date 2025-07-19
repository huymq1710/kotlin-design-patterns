package struction.composite

import struction.composite.editor.ImageEditor
import struction.composite.shapes.Circle
import struction.composite.shapes.CompoundShape
import struction.composite.shapes.Dot
import struction.composite.shapes.Rectangle
import java.awt.Color

fun main() {
    val editor = ImageEditor()

    editor.loadShapes(
        Circle(10, 10, 10, Color.BLUE),

        CompoundShape(
            Circle(110, 110, 50, Color.RED),
            Dot(160, 160, Color.RED)
        ),

        CompoundShape(
            Rectangle(250, 250, 100, 100, Color.GREEN),
            Dot(240, 240, Color.GREEN),
            Dot(240, 360, Color.GREEN),
            Dot(360, 360, Color.GREEN),
            Dot(360, 240, Color.GREEN)
        )
    )
}
