package behavior.command.editor

import behavior.command.commands.*
import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent

/**
 * GUI of text editor with Kotlin idiomatic improvements
 */
class Editor {
    lateinit var textField: JTextArea
    var clipboard: String = ""
    private val history = CommandHistory()

    fun init() {
        createAndShowGUI()
    }

    private fun createAndShowGUI() {
        SwingUtilities.invokeLater {
            val frame = JFrame("Text editor (type & use buttons, Luke!)")

            frame.apply {
                defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
                contentPane = createMainPanel()
                setSize(450, 200)
                setLocationRelativeTo(null)
                isVisible = true
            }
        }
    }

    private fun createMainPanel(): JPanel {
        val content = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
        }

        // Create text area
        textField = JTextArea().apply {
            lineWrap = true
        }
        content.add(textField)

        // Create buttons panel
        content.add(createButtonsPanel())

        return content
    }

    private fun createButtonsPanel(): JPanel {
        val buttons = JPanel(FlowLayout(FlowLayout.CENTER))

        val buttonConfigs = listOf(
            "Ctrl+C" to { executeCommand(CopyCommand(this)) },
            "Ctrl+X" to { executeCommand(CutCommand(this)) },
            "Ctrl+V" to { executeCommand(PasteCommand(this)) },
            "Ctrl+Z" to { undo() }
        )

        buttonConfigs.forEach { (text, action) ->
            buttons.add(createButton(text, action))
        }

        return buttons
    }

    private fun createButton(text: String, action: () -> Unit): JButton {
        return JButton(text).apply {
            addActionListener { action() }
        }
    }

    private fun executeCommand(command: Command) {
        if (command.execute()) {
            history.push(command)
        }
    }

    private fun undo() {
        history.pop()?.undo()
    }
}
