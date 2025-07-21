package behavior.memento.shapes

import java.awt.Color
import java.awt.Graphics

class Rectangle(
    x: Int,
    y: Int,
    private val rectWidth: Int,
    private val rectHeight: Int,
    color: Color
) : BaseShape(x, y, color) {

    override val width: Int get() = rectWidth
    override val height: Int get() = rectHeight

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.drawRect(x, y, width - 1, height - 1)
    }
}
