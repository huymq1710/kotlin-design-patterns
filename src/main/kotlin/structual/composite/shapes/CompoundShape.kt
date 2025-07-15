package structual.composite.shapes

import java.awt.Color
import java.awt.Graphics

class CompoundShape(vararg components: Shape) : BaseShape(0, 0, Color.BLACK) {
    private val children = mutableListOf<Shape>()

    init {
        add(*components)
    }

    fun add(component: Shape) {
        children.add(component)
    }

    fun add(vararg components: Shape) {
        children.addAll(components)
    }

    fun remove(child: Shape) {
        children.remove(child)
    }

    fun remove(vararg components: Shape) {
        children.removeAll(components.toSet())
    }

    fun clear() {
        children.clear()
    }

    override val x: Int
        get() = if (children.isEmpty()) 0 else children.minOfOrNull { it.x } ?: 0

    override val y: Int
        get() = if (children.isEmpty()) 0 else children.minOfOrNull { it.y } ?: 0

    override val width: Int
        get() {
            if (children.isEmpty()) return 0
            val leftmost = x
            return children.maxOfOrNull { (it.x - leftmost) + it.width } ?: 0
        }

    override val height: Int
        get() {
            if (children.isEmpty()) return 0
            val topmost = y
            return children.maxOfOrNull { (it.y - topmost) + it.height } ?: 0
        }

    override fun move(x: Int, y: Int) {
        children.forEach { it.move(x, y) }
    }

    override fun isInsideBounds(x: Int, y: Int): Boolean {
        return children.any { it.isInsideBounds(x, y) }
    }

    override fun unSelect() {
        super.unSelect()
        children.forEach { it.unSelect() }
    }

    fun selectChildAt(x: Int, y: Int): Boolean {
        return children.any { child ->
            if (child.isInsideBounds(x, y)) {
                child.select()
                true
            } else {
                false
            }
        }
    }

    override fun paint(graphics: Graphics) {
        if (isSelected) {
            enableSelectionStyle(graphics)
            graphics.drawRect(x - 1, y - 1, width + 1, height + 1)
            disableSelectionStyle(graphics)
        }

        children.forEach { it.paint(graphics) }
    }
}
