package behavior.memento.shapes


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
        get() = if (children.isEmpty()) 0 else children.minOf { it.x }

    override val y: Int
        get() = if (children.isEmpty()) 0 else children.minOf { it.y }

    override val width: Int
        get() {
            val leftX = x
            return children.maxOfOrNull { child ->
                val relativeX = child.x - leftX
                relativeX + child.width
            } ?: 0
        }

    override val height: Int
        get() {
            val topY = y
            return children.maxOfOrNull { child ->
                val relativeY = child.y - topY
                relativeY + child.height
            } ?: 0
        }

    override fun drag() {
        children.forEach { it.drag() }
    }

    override fun drop() {
        children.forEach { it.drop() }
    }

    override fun moveTo(x: Int, y: Int) {
        children.forEach { it.moveTo(x, y) }
    }

    override fun moveBy(x: Int, y: Int) {
        children.forEach { it.moveBy(x, y) }
    }

    override fun isInsideBounds(x: Int, y: Int): Boolean {
        return children.any { it.isInsideBounds(x, y) }
    }

    override var color: Color
        get() = super.color
        set(value) {
            super.color = value
            children.forEach { it.color = value }
        }

    override fun unSelect() {
        super.unSelect()
        children.forEach { it.unSelect() }
    }

    fun getChildAt(x: Int, y: Int): Shape? {
        return children.find { it.isInsideBounds(x, y) }
    }

    fun selectChildAt(x: Int, y: Int): Boolean {
        return getChildAt(x, y)?.let {
            it.select()
            true
        } ?: false
    }

    val selected: List<Shape>
        get() = children.filter { it.isSelected }

    override fun paint(graphics: Graphics) {
        if (isSelected) {
            graphics.enableSelectionStyle()
            graphics.drawRect(x - 1, y - 1, width + 1, height + 1)
            graphics.disableSelectionStyle()
        }

        children.forEach { it.paint(graphics) }
    }
}
