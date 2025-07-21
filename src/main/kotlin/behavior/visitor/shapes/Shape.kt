package behavior.visitor.shapes

import behavior.visitor.visitor.Visitor

/**
 * Common shape interface
 * Defines the basic operations that all shapes must support
 */
interface Shape {
    /**
     * Move the shape to a new position
     */
    fun move(x: Int, y: Int)
    
    /**
     * Draw the shape
     */
    fun draw()
    
    /**
     * Accept a visitor and delegate operation to it
     * This is the key method that enables the Visitor pattern
     */
    fun accept(visitor: Visitor): String
}
