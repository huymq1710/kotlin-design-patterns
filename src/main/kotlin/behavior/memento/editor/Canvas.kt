package behavior.memento.editor

import behavior.memento.commands.ColorCommand
import behavior.memento.commands.MoveCommand
import java.awt.*
import java.awt.event.*
import java.awt.image.BufferedImage
import javax.swing.*
import javax.swing.border.Border

class Canvas(private val editor: Editor) : java.awt.Canvas() {
    private val frame: JFrame

    companion object {
        private const val PADDING = 10
    }

    init {
        frame = createFrame()
        attachKeyboardListeners()
        attachMouseListeners()
        refresh()
    }

    private fun createFrame(): JFrame {
        return JFrame().apply {
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            setLocationRelativeTo(null)

            val contentPanel = JPanel().apply {
                val padding: Border = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
                border = padding
                layout = BoxLayout(this, BoxLayout.Y_AXIS)
                background = Color.LIGHT_GRAY

                add(JLabel("Select and drag to move."))
                add(JLabel("Right click to change color."))
                add(JLabel("Undo: Ctrl+Z, Redo: Ctrl+R"))
                add(this@Canvas)
            }

            contentPane = contentPanel
            isVisible = true
        }
    }

    private fun attachKeyboardListeners() {
        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.modifiers and KeyEvent.CTRL_MASK != 0) {
                    when (e.keyCode) {
                        KeyEvent.VK_Z -> editor.undo()
                        KeyEvent.VK_R -> editor.redo()
                    }
                }
            }
        })
    }

    private fun attachMouseListeners() {
        // Color changer
        val colorizer = object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                if (e.button != MouseEvent.BUTTON3) return

                editor.shapes.getChildAt(e.x, e.y)?.let {
                    val randomColor = Color((Math.random() * 0x1000000).toInt())
                    editor.execute(ColorCommand(editor, randomColor))
                    repaint()
                }
            }
        }
        addMouseListener(colorizer)

        // Shape selector
        val selector = object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                if (e.button != MouseEvent.BUTTON1) return

                val target = editor.shapes.getChildAt(e.x, e.y)
                val ctrlPressed = e.modifiers and ActionEvent.CTRL_MASK == ActionEvent.CTRL_MASK

                when {
                    target == null -> {
                        if (!ctrlPressed) editor.shapes.unSelect()
                    }
                    ctrlPressed -> {
                        if (target.isSelected) target.unSelect() else target.select()
                    }
                    else -> {
                        if (!target.isSelected) editor.shapes.unSelect()
                        target.select()
                    }
                }
                repaint()
            }
        }
        addMouseListener(selector)

        // Shape dragger
        val dragger = object : MouseAdapter() {
            private var moveCommand: MoveCommand? = null

            override fun mouseDragged(e: MouseEvent) {
                if (e.modifiersEx and MouseEvent.BUTTON1_DOWN_MASK == 0) return

                if (moveCommand == null) {
                    moveCommand = MoveCommand(editor).apply {
                        start(e.x, e.y)
                    }
                }
                moveCommand?.move(e.x, e.y)
                repaint()
            }

            override fun mouseReleased(e: MouseEvent) {
                if (e.button != MouseEvent.BUTTON1) return

                moveCommand?.let { command ->
                    command.stop(e.x, e.y)
                    editor.execute(command)
                    moveCommand = null
                    repaint()
                }
            }
        }
        addMouseListener(dragger)
        addMouseMotionListener(dragger)
    }

    override fun getWidth(): Int {
        return editor.shapes.x + editor.shapes.width + PADDING
    }

    override fun getHeight(): Int {
        return editor.shapes.y + editor.shapes.height + PADDING
    }

    fun refresh() {
        setSize(width, height)
        frame.pack()
    }

    override fun update(g: Graphics) {
        paint(g)
    }

    override fun paint(graphics: Graphics) {
        val buffer = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val ig2 = buffer.createGraphics()

        ig2.background = Color.WHITE
        ig2.clearRect(0, 0, width, height)

        editor.shapes.paint(buffer.graphics)
        graphics.drawImage(buffer, 0, 0, null)
    }
}
