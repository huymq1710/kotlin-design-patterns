package behavior.command.commands

import behavior.command.editor.Editor

/**
 * Abstract base command using sealed class for better type safety
 *
    A `sealed class` is a restricted class hierarchy where:

    - All subclasses must be known at compile time
    - All subclasses must be declared in the same file (or as nested classes)
    - It provides exhaustive when expressions - when (command) {
        is CopyCommand -> ...
        is CutCommand -> ...
        is PasteCommand -> ...
        // KHÔNG CẦN else branch!
    }
 */
sealed class Command(protected val editor: Editor) {
    private var backup: String? = null

    protected fun backup() {
        backup = editor.textField.text
    }

    open fun undo() {
        backup?.let { editor.textField.text = it }
    }

    abstract fun execute(): Boolean
}

/**
 * Copy selected text to clipboard
 */
class CopyCommand(editor: Editor) : Command(editor) {

    override fun execute(): Boolean {
        editor.clipboard = editor.textField.selectedText ?: ""
        return false // Copy doesn't modify content, so no need to save to history
    }
}

/**
 * Cut text to clipboard
 */
class CutCommand(editor: Editor) : Command(editor) {

    override fun execute(): Boolean {
        val selectedText = editor.textField.selectedText
        if (selectedText.isNullOrEmpty()) return false

        backup()
        val source = editor.textField.text
        editor.clipboard = selectedText
        editor.textField.text = source.cutString()
        return true
    }

    private fun String.cutString(): String {
        val start = substring(0, editor.textField.selectionStart)
        val end = substring(editor.textField.selectionEnd)
        return start + end
    }
}

/**
 * Paste text from clipboard
 */
class PasteCommand(editor: Editor) : Command(editor) {

    override fun execute(): Boolean {
        if (editor.clipboard.isBlank()) return false

        backup()
        val caretPosition = editor.textField.caretPosition
        editor.textField.insert(editor.clipboard, caretPosition)
        return true
    }
}
