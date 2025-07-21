package behavior.visitor.shapes

import behavior.visitor.visitor.Visitor

/**
 * A compound shape that can contain other shapes
 * Implements the Composite pattern along with Visitor
 */
class CompoundShape(
    val id: Int
) : Shape {
    
    private val _children = mutableListOf<Shape>()
    val children: List<Shape> get() = _children.toList()
    
    override fun move(x: Int, y: Int) {
        // Move compound shape implementation would go here
        println("Moving compound shape $id to position ($x, $y)")
        children.forEach { it.move(x, y) }
    }
    
    override fun draw() {
        // Draw compound shape implementation would go here
        println("Drawing compound shape $id")
        children.forEach { it.draw() }
    }
    
    override fun accept(visitor: Visitor): String {
        return visitor.visitCompoundGraphic(this)
    }
    
    /**
     * Add a shape to this compound shape
     */
    fun add(shape: Shape) {
        _children.add(shape)
    }
    
    /**
     * Remove a shape from this compound shape
     */
    fun remove(shape: Shape) {
        _children.remove(shape)
    }
}
