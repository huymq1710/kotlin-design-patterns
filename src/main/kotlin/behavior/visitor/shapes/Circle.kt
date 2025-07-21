package behavior.visitor.shapes

import behavior.visitor.visitor.Visitor

/**
 * A circle shape that extends Dot
 * Represents a circle with a center point and radius
 */
class Circle(
    id: Int,
    x: Int,
    y: Int,
    val radius: Int
) : Dot(id, x, y) {
    
    override fun accept(visitor: Visitor): String {
        return visitor.visitCircle(this)
    }
    
    override fun draw() {
        println("Drawing circle $id at position ($x, $y) with radius $radius")
    }
}
