package behavior.visitor.visitor

import behavior.visitor.shapes.Circle
import behavior.visitor.shapes.CompoundShape
import behavior.visitor.shapes.Dot
import behavior.visitor.shapes.Rectangle

/**
 * Common visitor interface
 * Defines visit methods for each concrete shape type
 * This is the core of the Visitor pattern - each method corresponds to a specific element type
 */
interface Visitor {
    /**
     * Visit a Dot shape
     */
    fun visitDot(dot: Dot): String
    
    /**
     * Visit a Circle shape
     */
    fun visitCircle(circle: Circle): String
    
    /**
     * Visit a Rectangle shape
     */
    fun visitRectangle(rectangle: Rectangle): String
    
    /**
     * Visit a CompoundShape
     */
    fun visitCompoundGraphic(compoundShape: CompoundShape): String
}
