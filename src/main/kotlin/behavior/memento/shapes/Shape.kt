package behavior.memento.shapes

import java.awt.Color
import java.awt.Graphics
import java.io.Serializable

interface Shape : Serializable {
    val x: Int
    val y: Int
    val width: Int
    val height: Int
    var color: Color
    var isSelected: Boolean

    fun drag()
    fun drop()
    fun moveTo(x: Int, y: Int)
    fun moveBy(x: Int, y: Int)
    fun isInsideBounds(x: Int, y: Int): Boolean
    fun select() { isSelected = true }
    fun unSelect() { isSelected = false }
    fun paint(graphics: Graphics)
}
