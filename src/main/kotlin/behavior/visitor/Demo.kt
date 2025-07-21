package behavior.visitor

import behavior.visitor.shapes.*
import behavior.visitor.visitor.XMLExportVisitor
import behavior.visitor.visitor.AreaCalculatorVisitor

/**
 * Demo class demonstrating the Visitor pattern
 * 
 * This example shows how to export different shapes to XML format
 * without modifying the shape classes themselves.
 * 
 * The Visitor pattern allows:
 * - Adding new operations (like XML export) without changing existing classes
 * - Keeping related operations together in visitor classes
 * - Type-safe operations through double dispatch
 */
fun main() {
    // Create various shapes
    val dot = Dot(id = 1, x = 10, y = 55)
    val circle = Circle(id = 2, x = 23, y = 15, radius = 10)
    val rectangle = Rectangle(id = 3, x = 10, y = 17, width = 20, height = 30)
    
    // Create a compound shape and add simple shapes to it
    val compoundShape = CompoundShape(id = 4).apply {
        add(dot)
        add(circle)
        add(rectangle)
    }
    
    // Create another compound shape and nest it
    val nestedCompound = CompoundShape(id = 5).apply {
        add(dot)
    }
    compoundShape.add(nestedCompound)
    
    // Export shapes to XML
    exportShapes(circle, compoundShape)
    
    // Calculate areas using another visitor
    calculateAreas(circle, compoundShape)
    
    // Demonstrate that shapes can still perform their original operations
    println("\n--- Shape Operations ---")
    dot.draw()
    circle.draw()
    rectangle.draw()
    compoundShape.draw()
}

/**
 * Export shapes to XML format using the Visitor pattern
 */
private fun exportShapes(vararg shapes: Shape) {
    val exportVisitor = XMLExportVisitor()
    println("--- XML Export ---")
    println(exportVisitor.export(*shapes))
}

/**
 * Calculate areas of shapes using another visitor
 * This demonstrates the extensibility of the Visitor pattern
 */
private fun calculateAreas(vararg shapes: Shape) {
    val areaVisitor = AreaCalculatorVisitor()
    println("--- Area Calculation ---")
    shapes.forEach { shape ->
        println(shape.accept(areaVisitor))
    }
    println()
}

