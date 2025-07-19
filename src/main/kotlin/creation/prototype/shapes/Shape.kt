package creation.prototype.shapes

abstract class Shape(
    open val x: Int,
    open val y: Int,
    open val color: String
) {
    abstract fun clone(): Shape

    override fun equals(other: Any?): Boolean {
        return other is Shape &&
                x == other.x &&
                y == other.y &&
                color == other.color
    }

    override fun hashCode(): Int = listOf(x, y, color).hashCode()
}
