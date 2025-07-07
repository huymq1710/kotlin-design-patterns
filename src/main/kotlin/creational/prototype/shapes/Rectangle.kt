package creational.prototype.shapes

class Rectangle(
    override val x: Int,
    override val y: Int,
    override val color: String,
    val width: Int,
    val height: Int
) : Shape(x, y, color) {

    override fun clone(): Shape = Rectangle(x, y, color, width, height)

    override fun equals(other: Any?): Boolean {
        return other is Rectangle &&
                super.equals(other) &&
                width == other.width &&
                height == other.height
    }

    override fun hashCode(): Int = listOf(super.hashCode(), width, height).hashCode()
}
