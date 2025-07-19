package struction.composite.shapes

import java.awt.Color
import java.awt.Graphics

class Rectangle(
    x: Int,
    y: Int,
    private val _width: Int,
    private val _height: Int,
    color: Color
) : BaseShape(x, y, color) {

    override val width: Int get() = _width
    override val height: Int get() = _height

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.drawRect(x, y, width - 1, height - 1)
    }
}
