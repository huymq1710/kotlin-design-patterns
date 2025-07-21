package behavior.memento.shapes

import java.awt.*

abstract class BaseShape(
    initialX: Int,
    initialY: Int,
    initialColor: Color
) : Shape {

    protected var _x = initialX
    protected var _y = initialY
    private var dx = 0
    private var dy = 0
    override var color = initialColor
    override var isSelected = false

    override val x: Int get() = _x
    override val y: Int get() = _y
    override val width: Int get() = 0
    override val height: Int get() = 0

    override fun drag() {
        dx = _x
        dy = _y
    }

    override fun moveTo(x: Int, y: Int) {
        _x = dx + x
        _y = dy + y
    }

    override fun moveBy(x: Int, y: Int) {
        _x += x
        _y += y
    }

    override fun drop() {
        _x = dx
        _y = dy
    }

    override fun isInsideBounds(x: Int, y: Int): Boolean {
        return x > this.x && x < (this.x + width) &&
                y > this.y && y < (this.y + height)
    }

    protected fun Graphics.enableSelectionStyle() {
        color = Color.LIGHT_GRAY
        val g2 = this as Graphics2D
        val dash = floatArrayOf(2.0f)
        g2.stroke = BasicStroke(
            1.0f,
            BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_MITER,
            2.0f, dash, 0.0f
        )
    }

    protected fun Graphics.disableSelectionStyle() {
        color = this@BaseShape.color
        val g2 = this as Graphics2D
        g2.stroke = BasicStroke()
    }

    override fun paint(graphics: Graphics) {
        if (isSelected) {
            graphics.enableSelectionStyle()
        } else {
            graphics.disableSelectionStyle()
        }
    }
}
