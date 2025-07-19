package struction.decorator.decorators.impl

import struction.decorator.decorators.DataSource
import struction.decorator.decorators.DataSourceDecorator
import java.io.*
import java.util.*
import java.util.zip.*

class CompressionDecorator(source: DataSource) : DataSourceDecorator(source) {

    var compressionLevel: Int = 6
        set(value) {
            field = value.coerceIn(0, 9)
        }

    override fun writeData(data: String) {
        super.writeData(compress(data))
    }

    override fun readData(): String {
        return decompress(super.readData())
    }

    private fun compress(stringData: String): String {
        val data = stringData.toByteArray()

        return try {
            ByteArrayOutputStream(512).use { bout ->
                DeflaterOutputStream(bout, Deflater(compressionLevel)).use { dos ->
                    dos.write(data)
                }
                Base64.getEncoder().encodeToString(bout.toByteArray())
            }
        } catch (ex: IOException) {
            stringData // Return original data if compression fails
        }
    }

    private fun decompress(stringData: String): String {
        val data = Base64.getDecoder().decode(stringData)

        return try {
            ByteArrayInputStream(data).use { input ->
                InflaterInputStream(input).use { inflater ->
                    ByteArrayOutputStream(512).use { bout ->
                        inflater.copyTo(bout)
                        String(bout.toByteArray())
                    }
                }
            }
        } catch (ex: IOException) {
            stringData // Return original data if decompression fails
        }
    }
}
