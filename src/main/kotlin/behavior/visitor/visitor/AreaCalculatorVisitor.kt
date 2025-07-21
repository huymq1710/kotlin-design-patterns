package behavior.visitor.visitor

import behavior.visitor.shapes.Circle
import behavior.visitor.shapes.CompoundShape
import behavior.visitor.shapes.Dot
import behavior.visitor.shapes.Rectangle

/**
 * Another concrete visitor that calculates the area of shapes
 * This demonstrates how easy it is to add new operations using the Visitor pattern
 * without modifying existing shape classes
 */
class AreaCalculatorVisitor : Visitor {
    
    override fun visitDot(dot: Dot): String {
        // A dot has no area
        return "Dot ${dot.id}: Area = 0"
    }
    
    override fun visitCircle(circle: Circle): String {
        val area = Math.PI * circle.radius * circle.radius
        return "Circle ${circle.id}: Area = %.2f".format(area)
    }
    
    override fun visitRectangle(rectangle: Rectangle): String {
        val area = rectangle.width * rectangle.height
        return "Rectangle ${rectangle.id}: Area = $area"
    }
    
    override fun visitCompoundGraphic(compoundShape: CompoundShape): String {
        val results = mutableListOf<String>()
        results.add("Compound Shape ${compoundShape.id}:")
        
        var totalArea = 0.0
        compoundShape.children.forEach { shape ->
            val result = shape.accept(this)
            results.add("  - $result")
            
            // Extract numeric area value for total calculation
            when (shape) {
                is Circle -> totalArea += Math.PI * shape.radius * shape.radius
                is Rectangle -> totalArea += shape.width * shape.height
                // Dots have zero area, compound shapes would need recursive calculation
            }
        }
        
        results.add("  Total Area = %.2f".format(totalArea))
        return results.joinToString("\n")
    }
}
