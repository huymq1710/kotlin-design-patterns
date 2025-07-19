package structual.decorator

import structual.decorator.decorators.DataSourceDecorator
import structual.decorator.decorators.FileDataSource
import structual.decorator.decorators.impl.CompressionDecorator
import structual.decorator.decorators.impl.EncryptionDecorator
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun main() {
    val salaryRecords = """
        Name,Salary
        John Smith,100000
        Steven Jobs,912000
    """.trimIndent()

    // Ensure the output directory exists
    val outputDir = File("out")
    if (!outputDir.exists()) {
        outputDir.mkdirs()
        println("Created output directory: ${outputDir.absolutePath}")
    }

    val fileName = "out/OutputDemo.txt"

    // Create decorated data source with both compression and encryption
    val encoded: DataSourceDecorator = CompressionDecorator(
        EncryptionDecorator(
            FileDataSource(fileName)
        )
    )

    println("Writing data to: ${File(fileName).absolutePath}")
    encoded.writeData(salaryRecords)

    val plain = FileDataSource(fileName)

    println("\n- Input ----------------")
    println(salaryRecords)
    println("\n- Encoded (raw file content) --------------")
    val rawContent = plain.readData()
    println(rawContent.ifEmpty { "No data or file not found" })
    println("\n- Decoded (through decorators) --------------")
    val decodedContent = encoded.readData()
    println(decodedContent.ifEmpty { "No data or decoding failed" })
}

