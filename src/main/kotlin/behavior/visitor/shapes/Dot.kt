package behavior.visitor.shapes

import behavior.visitor.visitor.Visitor

/**
 * A dot shape implementation
 * Represents a simple point with coordinates and an id
 */
open class Dot(
    val id: Int = 0,
    val x: Int = 0,
    val y: Int = 0
) : Shape {
    
    override fun move(x: Int, y: Int) {
        // Move shape implementation would go here
        println("Moving dot $id to position ($x, $y)")
    }
    
    override fun draw() {
        // Draw shape implementation would go here
        println("Drawing dot $id at position ($x, $y)")
    }
    
    override fun accept(visitor: Visitor): String {
        return visitor.visitDot(this)
    }
}
