package behavior.visitor.visitor

import behavior.visitor.shapes.Circle
import behavior.visitor.shapes.CompoundShape
import behavior.visitor.shapes.Dot
import behavior.visitor.shapes.Rectangle
import behavior.visitor.shapes.Shape

/**
 * Concrete visitor that exports all shapes into XML format
 * Demonstrates how the Visitor pattern allows adding new operations
 * without modifying the existing shape classes
 */
class XMLExportVisitor : Visitor {
    
    /**
     * Export multiple shapes to XML format
     */
    fun export(vararg shapes: Shape): String {
        return buildString {
            appendLine("""<?xml version="1.0" encoding="utf-8"?>""")
            shapes.forEach { shape ->
                appendLine(shape.accept(this@XMLExportVisitor))
            }
        }
    }
    
    override fun visitDot(dot: Dot): String {
        return buildString {
            appendLine("<dot>")
            appendLine("    <id>${dot.id}</id>")
            appendLine("    <x>${dot.x}</x>")
            appendLine("    <y>${dot.y}</y>")
            append("</dot>")
        }
    }
    
    override fun visitCircle(circle: Circle): String {
        return buildString {
            appendLine("<circle>")
            appendLine("    <id>${circle.id}</id>")
            appendLine("    <x>${circle.x}</x>")
            appendLine("    <y>${circle.y}</y>")
            appendLine("    <radius>${circle.radius}</radius>")
            append("</circle>")
        }
    }
    
    override fun visitRectangle(rectangle: Rectangle): String {
        return buildString {
            appendLine("<rectangle>")
            appendLine("    <id>${rectangle.id}</id>")
            appendLine("    <x>${rectangle.x}</x>")
            appendLine("    <y>${rectangle.y}</y>")
            appendLine("    <width>${rectangle.width}</width>")
            appendLine("    <height>${rectangle.height}</height>")
            append("</rectangle>")
        }
    }
    
    override fun visitCompoundGraphic(compoundShape: CompoundShape): String {
        return buildString {
            appendLine("<compound_graphic>")
            appendLine("   <id>${compoundShape.id}</id>")
            append(visitCompoundGraphicChildren(compoundShape))
            append("</compound_graphic>")
        }
    }
    
    /**
     * Helper method to process children of compound shapes with proper indentation
     */
    private fun visitCompoundGraphicChildren(compoundShape: CompoundShape): String {
        return buildString {
            compoundShape.children.forEach { shape ->
                val childXml = shape.accept(this@XMLExportVisitor)
                // Add proper indentation for sub-objects
                val indentedXml = childXml.lines().joinToString("\n") { "    $it" }
                appendLine(indentedXml)
            }
        }
    }
}
