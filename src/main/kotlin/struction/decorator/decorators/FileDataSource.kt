package struction.decorator.decorators

import java.io.File
import java.io.IOException

class FileDataSource(private val name: String) : DataSource {

    override fun writeData(data: String) {
        val file = File(name)
        try {
            // Create parent directories if they don't exist
            file.parentFile?.mkdirs()

            file.outputStream().use { fos ->
                fos.write(data.toByteArray())
            }
        } catch (ex: IOException) {
            println("Error writing to file: ${ex.message}")
        }
    }

    override fun readData(): String {
        val file = File(name)
        return try {
            if (!file.exists()) {
                println("File does not exist: $name")
                return ""
            }
            file.readText()
        } catch (ex: IOException) {
            println("Error reading from file: ${ex.message}")
            ""
        }
    }
}
