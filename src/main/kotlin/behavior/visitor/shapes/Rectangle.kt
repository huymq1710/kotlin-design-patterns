package behavior.visitor.shapes

import behavior.visitor.visitor.Visitor

/**
 * A rectangle shape implementation
 * Represents a rectangle with position, width, and height
 */
class Rectangle(
    val id: Int,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int
) : Shape {
    
    override fun move(x: Int, y: Int) {
        // Move shape implementation would go here
        println("Moving rectangle $id to position ($x, $y)")
    }
    
    override fun draw() {
        // Draw shape implementation would go here
        println("Drawing rectangle $id at position ($x, $y) with size ${width}x$height")
    }
    
    override fun accept(visitor: Visitor): String {
        return visitor.visitRectangle(this)
    }
}
