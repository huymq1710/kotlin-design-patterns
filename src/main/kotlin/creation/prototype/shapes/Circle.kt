package creation.prototype.shapes

class Circle(
    override val x: Int,
    override val y: Int,
    override val color: String,
    val radius: Int
) : Shape(x, y, color) {

    override fun clone(): Shape = Circle(x, y, color, radius)

    override fun equals(other: Any?): Boolean {
        return other is Circle &&
                super.equals(other) &&
                radius == other.radius
    }

    override fun hashCode(): Int = listOf(super.hashCode(), radius).hashCode()
}
