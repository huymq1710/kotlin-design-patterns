package behavior.memento.shapes

import java.awt.Color
import java.awt.Graphics

class Dot(
    x: Int,
    y: Int,
    color: Color
) : BaseShape(x, y, color) {

    companion object {
        private const val DOT_SIZE = 3
    }

    override val width: Int get() = DOT_SIZE
    override val height: Int get() = DOT_SIZE

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.fillRect(x - 1, y - 1, width, height)
    }
}
