package structual.composite.shapes

import java.awt.*

abstract class BaseShape(
    protected var _x: Int,
    protected var _y: Int,
    protected val color: Color
) : Shape {

    private var selected = false

    override val x: Int get() = _x
    override val y: Int get() = _y
    override val width: Int get() = 0
    override val height: Int get() = 0
    override val isSelected: Boolean get() = selected

    override fun move(x: Int, y: Int) {
        this._x += x
        this._y += y
    }

    override fun isInsideBounds(x: Int, y: Int): Boolean {
        return x > this.x && x < (this.x + width) &&
                y > this.y && y < (this.y + height)
    }

    override fun select() {
        selected = true
    }

    override fun unSelect() {
        selected = false
    }

    protected fun enableSelectionStyle(graphics: Graphics) {
        graphics.color = Color.LIGHT_GRAY

        val g2 = graphics as Graphics2D
        val dash = floatArrayOf(2.0f)
        g2.stroke = BasicStroke(
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            2.0f,
            dash,
            0.0f
        )
    }

    protected fun disableSelectionStyle(graphics: Graphics) {
        graphics.color = color
        val g2 = graphics as Graphics2D
        g2.stroke = BasicStroke()
    }

    override fun paint(graphics: Graphics) {
        if (isSelected) {
            enableSelectionStyle(graphics)
        } else {
            disableSelectionStyle(graphics)
        }
        // ... additional painting logic in subclasses
    }
}
