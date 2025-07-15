package structual.composite.editor

import structual.composite.shapes.CompoundShape
import structual.composite.shapes.Shape
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

private const val PADDING = 10

class ImageEditor {
    private val canvas = EditorCanvas()
    private val allShapes = CompoundShape()

    fun loadShapes(vararg shapes: Shape) {
        allShapes.clear()
        allShapes.add(*shapes)
        canvas.refresh()
    }

    private inner class EditorCanvas : Canvas() {
        private val frame = JFrame()

        init {
            createFrame()
            refresh()
            addMouseListener(object : MouseAdapter() {
                override fun mousePressed(e: MouseEvent) {
                    allShapes.unSelect()
                    allShapes.selectChildAt(e.x, e.y)
                    e.component.repaint()
                }
            })
        }

        private fun createFrame() {
            frame.apply {
                defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
                setLocationRelativeTo(null)

                val contentPanel = JPanel()
                val padding = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
                contentPanel.border = padding
                contentPane = contentPanel

                add(this@EditorCanvas)
                isVisible = true
                contentPane.background = Color.LIGHT_GRAY
            }
        }

        override fun getWidth(): Int {
            return allShapes.x + allShapes.width + PADDING
        }

        override fun getHeight(): Int {
            return allShapes.y + allShapes.height + PADDING
        }

        fun refresh() {
            setSize(width, height)
            frame.pack()
        }

        override fun paint(graphics: Graphics) {
            allShapes.paint(graphics)
        }
    }
}
